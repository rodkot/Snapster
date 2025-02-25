package ru.sbertech.platformv.print.benchmark.conversion;

import com.lowagie.text.DocumentException;

import org.xhtmlrenderer.extend.UserAgentCallback;
import org.xhtmlrenderer.pdf.ITextRenderer;
import org.xhtmlrenderer.resource.CSSResource;
import org.xhtmlrenderer.resource.ImageResource;
import org.xhtmlrenderer.resource.XMLResource;

import ru.sbertech.platformv.print.benchmark.domain.service.ResourceResolverService;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.util.logging.Logger;

public class FlyingSaucerConverter {

    private final ITextRenderer renderer;

    public FlyingSaucerConverter(String content, String baseURL, ResourceResolverService resourceResolverService) {
        renderer = new ITextRenderer();
        renderer.getSharedContext().getUserAgentCallback().setBaseURL(baseURL);
        final UserAgentCallback callback = renderer.getSharedContext().getUserAgentCallback();
        renderer.getSharedContext().setUserAgentCallback(new UserAgentCallback() {
            @Override
            public String resolveURI(String uri) {
                return uri;
            }

            private String getTLPath(String uri) {

                if (!uri.startsWith("file:")) {
                    if (uri.startsWith("/")) {
                        uri = uri.substring(1);
                    }
                    int parameterStart = uri.lastIndexOf("?");
                    if (parameterStart >= 0) {
                        uri = uri.substring(0, parameterStart);
                    }
                    URL file = resourceResolverService.getResourceURL(uri);
                    return file == null ? uri : file.toExternalForm();
                }
                return uri;
            }

            @Override
            public String getBaseURL() {
                return callback.getBaseURL();
            }

            @Override
            public void setBaseURL(String url) {
                callback.setBaseURL(url);
            }

            @Override
            public boolean isVisited(String uri) {
                return callback.isVisited(uri);
            }

            @Override
            public XMLResource getXMLResource(String uri) {
                return callback.getXMLResource(uri);
            }

            @Override
            public ImageResource getImageResource(String uri) {
                uri = getTLPath(uri);
                return callback.getImageResource(uri);
            }

            @Override
            public CSSResource getCSSResource(String uri) {
                uri = getTLPath(uri);
                return callback.getCSSResource(uri);
            }

            @Override
            public byte[] getBinaryResource(String uri) {
                uri = getTLPath(uri);
                return callback.getBinaryResource(uri);
            }
        });
        renderer.setDocumentFromString(content);
        renderer.layout();
    }

    public void convert(OutputStream outputStream) throws DocumentException {
        renderer.createPDF(outputStream);
    }
}

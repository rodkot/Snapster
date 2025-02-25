package ru.sbertech.platformv.print.benchmark.conversion.flyingsaucer;

import com.lowagie.text.Image;
import fr.opensagres.xdocreport.core.io.IOUtils;
import org.jetbrains.annotations.NotNull;
import org.w3c.dom.Element;
import org.xhtmlrenderer.extend.FSImage;
import org.xhtmlrenderer.extend.ReplacedElement;
import org.xhtmlrenderer.extend.ReplacedElementFactory;
import org.xhtmlrenderer.extend.UserAgentCallback;
import org.xhtmlrenderer.layout.LayoutContext;
import org.xhtmlrenderer.pdf.ITextFSImage;
import org.xhtmlrenderer.pdf.ITextImageElement;
import org.xhtmlrenderer.render.BlockBox;
import org.xhtmlrenderer.simple.extend.FormSubmissionListener;
import ru.sbertech.platformv.print.benchmark.domain.service.ResourceResolverService;

import java.io.FileInputStream;
import java.io.InputStream;

//TODO: ReplacedElementFactory не видит таблицу стилей CSS
public class MediaReplacedElementFactory implements ReplacedElementFactory {
    private final ReplacedElementFactory superFactory;
    private final ResourceResolverService resourceResolverService;

    public MediaReplacedElementFactory(ReplacedElementFactory superFactory, ResourceResolverService resourceResolverService) {
        this.superFactory = superFactory;
        this.resourceResolverService = resourceResolverService;
    }

    @Override
    public ReplacedElement createReplacedElement(@NotNull LayoutContext layoutContext, BlockBox blockBox, @NotNull UserAgentCallback userAgentCallback, int cssWidth, int cssHeight) {
        Element element = blockBox.getElement();
        if (element == null) {
            return null;
        }
        String nodeName = element.getNodeName();
        if ("img".equals(nodeName)) {
            if (!element.hasAttribute("src")) {
                throw new RuntimeException("An element `img` is missing a `src` attribute indicating the media file.");
            }
            InputStream input = null;
            try {
                input = new FileInputStream(resourceResolverService.getFileFromResource(element.getAttribute("src")));

                final byte[] bytes = IOUtils.toByteArray(input);
                final Image image = Image.getInstance(bytes);
                final FSImage fsImage = new ITextFSImage(image);

                if (fsImage != null) {
                    if ((cssWidth != -1) || (cssHeight != -1)) {
                        fsImage.scale(cssWidth, cssHeight);
                    }

                    return new ITextImageElement(fsImage);
                }
            } catch (Exception e) {
                throw new RuntimeException("There was a problem trying to read a template embedded graphic.", e);
            } finally {
                IOUtils.closeQuietly(input);
            }
        }
        return this.superFactory.createReplacedElement(layoutContext, blockBox, userAgentCallback, cssWidth, cssHeight);
    }

    @Override
    public void reset() {
        this.superFactory.reset();
    }

    @Override
    public void remove(@NotNull Element e) {
        this.superFactory.remove(e);
    }

    @Override
    public void setFormSubmissionListener(@NotNull FormSubmissionListener listener) {
        this.superFactory.setFormSubmissionListener(listener);
    }
}
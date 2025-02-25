package ru.sbertech.platformv.print.benchmark.conversion.flyingsaucer;

import com.lowagie.text.DocumentException;
import org.xhtmlrenderer.pdf.ITextRenderer;
import ru.sbertech.platformv.print.benchmark.domain.service.ResourceResolverService;

import java.io.OutputStream;

public class FlyingSaucerConverter {

    private final ITextRenderer renderer;

    public FlyingSaucerConverter(String content, ResourceResolverService resourceResolverService) {
        renderer = new ITextRenderer();
        renderer.getSharedContext().setReplacedElementFactory(new MediaReplacedElementFactory(renderer.getSharedContext().getReplacedElementFactory(), resourceResolverService));
        renderer.setDocumentFromString(content);
        renderer.layout();
    }

    public void convert(OutputStream outputStream) throws DocumentException {
        renderer.createPDF(outputStream);
    }
}

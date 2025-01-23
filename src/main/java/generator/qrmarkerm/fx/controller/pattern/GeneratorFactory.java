package generator.qrmarkerm.fx.controller.pattern;

import generator.qrmarkerm.generator.ZxingGenerator;
import generator.qrmarkerm.generator.ZxingGeneratorWithLogo;
import javafx.scene.image.ImageView;

public class GeneratorFactory {
    public static Generator getGenerator(final ImageView logoImageView) {

        if (logoImageView.getImage() == null) {
            return new ZxingGenerator();
        } else {
            return new ZxingGeneratorWithLogo(logoImageView);
        }
    }
}
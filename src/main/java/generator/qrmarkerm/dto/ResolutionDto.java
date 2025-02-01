package generator.qrmarkerm.dto;

public class ResolutionDto {
    private final int width;
    private final int height;

    public ResolutionDto(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
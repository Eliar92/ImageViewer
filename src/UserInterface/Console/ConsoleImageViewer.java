package UserInterface.Console;
import UserInterface.ImageViewer;
import Model.Image;
public class ConsoleImageViewer implements ImageViewer {
    private Image image;
    @Override
    public Image getImage() {
        return image;
    }
    @Override
    public void setImage(Image image) {
        this.image = image;
        refresh();
    }
    @Override
    public void refresh() {
        System.out.print(getImage().getBitmap().getByteArray().length);
    }
}
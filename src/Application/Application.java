package Application;
import Model.Image;
import UserInterface.ImageViewer;
import Persistence.ImageLoader;
import UserInterface.Swing.ApplicationFrame;
import Control.NextImageCommand;
import Control.PrevImageCommand;
import Model.Bitmap;
import Model.ProxyImage;
import Model.RealImage;
import UserInterface.Swing.SwingImageViewerPanel;
import java.awt.event.ActionListener;
public class Application {
    public static void main(String[] args) {
        new Application().execute();
    }
    private void execute() {
        Image[] images = linkImages(createImages());
        ImageViewer viewer = createImageViewer(images[0]);
        createApplicationFrame(createCommands(viewer),(SwingImageViewerPanel) viewer);
    }
    private Image[] createImages() {
    Image[] images = new Image[6];
    for (int i = 0; i < images.length; i++) images[i] = createImage(i);
    return images;
    }
    private Image createImage(final int index) {
        final String ROOT = "C://Users//Eli//Documents//NetBeansProjects//ImageViewer//Images//";
        final String[] images = {"Playa1.jpeg", "Playa2.jpg", "Playa3.jpg", "Playa4.jpg", "Playa5.jpg", "Campo.jpg"};
        return new ProxyImage(new ImageLoader() {
            @Override
            public Image load() {
                return new RealImage(new Bitmap(ROOT + images[index]));
            }
        });
}
    private Image[] linkImages(Image[] images) {
        for (int i = 0; i < images.length; i++) {
            Image image = images[i];
            Image next = images[(i + 1) % images.length];
            Image prev = images[(i + images.length - 1) % images.length];
            image.setNext(next);
            image.setPrev(prev);
        }
        return images;
    }
    private SwingImageViewerPanel createImageViewer(Image image) {
        SwingImageViewerPanel viewer = new SwingImageViewerPanel();
        viewer.setImage(image);
        return viewer;
    }
    private ApplicationFrame createApplicationFrame(ActionListener[] listeners, SwingImageViewerPanel viewer) {
        return new ApplicationFrame(listeners, viewer);
    }
    private ActionListener[] createCommands(ImageViewer viewer) {
        return new ActionListener[] {
            new PrevImageCommand(viewer),
            new NextImageCommand(viewer)
        };
    }
}
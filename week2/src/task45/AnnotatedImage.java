package task45;
import java.util.Objects;

public class AnnotatedImage {
    private final String imagePath;
    private final Annotation[] annotations;

    public AnnotatedImage(String imagePath, Annotation... annotations) {
        this.imagePath = imagePath;
        this.annotations = (Annotation[]) annotations;
    }

    public String getImagePath() {
        return this.imagePath;
    }

    public Annotation[] getAnnotations() {
        return this.annotations;
    }

    public Annotation findByPoint(int x, int y) {
        for (Annotation annotation : this.annotations) {
            if ((annotation.getElem().x0 == x && annotation.getElem().y0 == y) ||
                    (Objects.equals(annotation.getElem().type, "R") &&
                            annotation.getElem().x1 == x && annotation.getElem().y1 == y)) {
                return annotation;
            }
        }
        return null;
    }

    public Annotation findByLabel(String label) {
        for (Annotation annotation : this.annotations) {
            if (annotation.getLabel().contains(label)) {
                return annotation;
            }
        }
        return null;
    }
}

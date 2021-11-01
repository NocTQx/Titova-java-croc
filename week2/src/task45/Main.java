package task45;
import java.util.Objects;

public class Main {

        public static void main(String[] args) {

            Figure rect1 = new Rectangle(2 ,2, 4 ,4);
            Figure rect2 = new Rectangle(10 ,10, 20 ,20);
            Figure round1 = new Round(5, 5, 3);
            Figure round2 = new Round(0, 0, 100);

            AnnotatedImage img = new task45.AnnotatedImage("img",
                    new Annotation(rect1, "здесь что-то написано "),
                    new Annotation(rect2, "и здесь"),
                    new Annotation(round1, "это круг"),
                    new Annotation(round2, "это тоже круг"));

            Annotation elemTest = img.findByPoint(0, 0);
            System.out.println(elemTest);
            elemTest.getElem().move(1,2);
            System.out.println(elemTest);

            System.out.println(img.findByLabel("здесь"));
            System.out.println(img.findByLabel("и"));

            System.out.println(img.getImagePath());
            System.out.println(img.getAnnotations()[0]);
            System.out.println(img.getAnnotations()[1]);
            System.out.println(img.getAnnotations()[2]);
            System.out.println(img.getAnnotations()[3]);

        }
}

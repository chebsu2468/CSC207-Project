package AppPkg;
import java.awt.Color;

public class Setting{
    private int textSize;
    private Color textColor;
    private String textStyle;

    public Setting() {
        this.textSize = 13;
        this.textColor = new Color(100, 50, 200);
        this.textStyle = new String("Times New Roman");
    }
    public int getTextSize() {
        return this.textSize;
        }
    public Color getTextColor() {
        return this.textColor;
        }
    public String getTextStyle()  {
        return this.textStyle;
    }

}


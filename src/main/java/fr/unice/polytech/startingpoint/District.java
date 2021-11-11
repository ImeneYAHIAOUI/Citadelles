package proposition1;

public class District implements IDistrict{
    private int val;
    private Color color;
    private String name;

    public District(int val, Color color, String name) {
        this.val = val;
        this.color = color;
        this.name = name;
    }

    @Override
    public int getVal() {
        return this.val;
    }

    @Override
    public Color getColor() {
        return this.color;
    }

    @Override
    public boolean isWonder() {
        return false;
    }
}

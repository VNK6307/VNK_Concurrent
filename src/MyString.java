public class MyString {
    private int maxAmount;
    String text;

    public MyString(int maxAmount, String text) {
        this.maxAmount = maxAmount;
        this.text = text;
    }

    public int getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(int maxAmount) {
        this.maxAmount = maxAmount;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return
                "maxAmount = " + maxAmount +
                ", text = " + text;
    }
}// class

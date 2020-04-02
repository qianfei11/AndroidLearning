package zjgsu.is.qff.homework05;

import androidx.annotation.NonNull;

public class Drink {
    private String name;
    private String description;
    private int imageResourceId;

    public Drink(String name, String description, int imageResourceId) {
        this.name = name;
        this.description = description;
        this.imageResourceId = imageResourceId;
    }

    public static final Drink[] drinks = {
            new Drink("Latte", "sepresso with steamed milk", R.drawable.latte),
            new Drink("Coppuccino", "Espresso, hot milk, steamed milk foam", R.drawable.cappuccino),
            new Drink("Filter", "beans & brewed fresh", R.drawable.filter)
    };

    @NonNull
    @Override
    public String toString() {
        return this.name;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }
}

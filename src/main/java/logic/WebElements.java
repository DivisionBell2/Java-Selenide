package logic;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import java.util.Random;

public interface WebElements {
    default SelenideElement selectRandomElementFromCollection(ElementsCollection collection) {
        return collection.get(new Random().nextInt(collection.size()));
    }

    default SelenideElement selectElementFromCollectionByText(ElementsCollection collection, String name) {
        return collection
                .stream()
                .filter(val-> val.has(Condition.text(name)))
                .findFirst()
                .orElseThrow(NoSuchFieldError::new);
    }
}
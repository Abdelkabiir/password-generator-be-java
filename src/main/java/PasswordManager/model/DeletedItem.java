package PasswordManager.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "deleted-items")
public class DeletedItem {
    @Id
    public String id;
    public String itemId;
    public ItemsType itemType;

    public DeletedItem(String itemId, ItemsType itemType) {
        this.itemId = itemId;
        this.itemType = itemType;
    }

    public String getItemId() {
        return itemId;
    }

    public String getId() {
        return id;
    }


    public ItemsType getItemType() {
        return itemType;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public void setItemType(ItemsType itemType) {
        this.itemType = itemType;
    }
}

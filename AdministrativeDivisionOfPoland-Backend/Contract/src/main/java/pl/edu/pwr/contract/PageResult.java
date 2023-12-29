package pl.edu.pwr.contract;

import java.util.List;

public class PageResult<T> {
    public List<T> items;
    public int totalPages;
    public int itemsFrom;
    public int itemsTo;
    public int totalItemsCount;

    public PageResult(List<T> items, int totalItemsCount, int pageSize, int pageNumber) {
        this.items = items;
        this.totalItemsCount = totalItemsCount;
        this.itemsFrom = pageSize * (pageNumber - 1) + 1;
        this.itemsTo = itemsFrom + pageSize - 1;
        this.totalPages = (int) Math.ceil((double) totalItemsCount / pageSize);
    }
}

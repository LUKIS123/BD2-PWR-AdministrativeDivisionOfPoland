package pl.edu.pwr.contract.Common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class PageResult<T> {
    public List<T> items;
    public Integer totalPages;
    public Integer itemsFrom;
    public Integer itemsTo;
    public Integer totalItemsCount;

    public PageResult(List<T> items, int totalItemsCount, int pageSize, int pageNumber) {
        this.items = items;
        this.totalItemsCount = totalItemsCount;
        this.itemsFrom = pageSize * (pageNumber - 1) + 1;
        this.itemsTo = itemsFrom + pageSize - 1;
        this.totalPages = (int) Math.ceil((double) totalItemsCount / pageSize);
    }
}

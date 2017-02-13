package thesis.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * A list of DTOs
 * @author Alex
 *
 * @param <T> Type of the DTOs
 */
public class DtoList<T extends Dto> extends ArrayList<T> {

    public DtoList() {
        super();
    }

    public DtoList(List<T> list) {
        super(list);
    }

    @Override
    public String toString() {
        if (!this.isEmpty()) return "DtoList of " + this.get(0).getClass().getSimpleName() + ": " + super.toString();
        else return "[Empty DtoList]";
    }
}
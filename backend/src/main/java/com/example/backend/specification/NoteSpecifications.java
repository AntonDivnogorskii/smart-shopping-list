package com.example.backend.specification;


import com.example.backend.model.Note;
import com.example.backend.request.NoteFilterRequest;
import org.springframework.data.jpa.domain.Specification;
import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class NoteSpecifications {

    public static Specification<Note> withFilter(NoteFilterRequest filter) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filter != null) {
                // Фильтр по индикатору (сделано/не сделано)
                if (filter.getIndicator() != null) {
                    predicates.add(cb.equal(root.get("indicator"), filter.getIndicator()));
                }

                // Фильтр по количеству
                if (filter.getQuantity() != null) {
                    predicates.add(cb.equal(root.get("quantity"), filter.getQuantity()));
                }
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}

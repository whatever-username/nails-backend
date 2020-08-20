package whatever.nails.mapper;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;

public abstract class AbstractMapper<F, T> {

    @Autowired
    ModelMapper mapper;

    private Class<F> fromClass;
    private Class<T> toClass;

    AbstractMapper(Class<F> fromClass, Class<T> toClass) {
        this.fromClass = fromClass;
        this.toClass = toClass;
    }
    public T to(F from){
        return mapper.map(from,toClass);
    }
    public F from(T to){
        return mapper.map(to,fromClass);
    }
    Converter<F, T> toDtoConverter() {
        return context -> {
            F source = context.getSource();
            T destination = context.getDestination();
            mapSpecificFieldsFrom(source, destination);
            return context.getDestination();
        };
    }

    Converter<T, F> toEntityConverter() {
        return context -> {
            T source = context.getSource();
            F destination = context.getDestination();
            mapSpecificFieldsTo(source, destination);
            return context.getDestination();
        };
    }

    void mapSpecificFieldsTo(T source, F destination) {

    }
    void mapSpecificFieldsFrom(F destination, T source) {

    }




    public <D> D convert(Object source, Class<D> destinationType) {
        return mapper.map(source, destinationType);
    }


}

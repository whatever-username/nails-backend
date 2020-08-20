package whatever.nails.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import whatever.nails.dto.RegistrationDto;
import whatever.nails.entity.auth.User;

import javax.annotation.PostConstruct;
@Component
public class RegistrationDtoToUserMapper {
    private final ModelMapper mapper;


    @Autowired
    public RegistrationDtoToUserMapper(
            ModelMapper mapper) {
        this.mapper = mapper;
    }

    @PostConstruct
    public void setup() {

        mapper.typeMap(User.class, RegistrationDto.class)
                .setPostConverter(ctx -> {
                    mapCustom(ctx.getSource(), ctx.getDestination());
                    return ctx.getDestination();
                });
        mapper.typeMap(RegistrationDto.class, User.class)
                .setPostConverter(ctx -> {
                    mapCustom(ctx.getSource(), ctx.getDestination());
                    return ctx.getDestination();
                });
    }

    void mapCustom(User source, RegistrationDto destination) {
    }
    void mapCustom(RegistrationDto source, User destination) {
        System.out.println(source);
        System.out.println(destination);
    }

    public <D> D convert(Object source, Class<D> destinationType) {
        return mapper.map(source, destinationType);
    }
}

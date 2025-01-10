package de.unibayreuth.se.taskboard.api.mapper;

import de.unibayreuth.se.taskboard.api.dtos.UserDto;
import de.unibayreuth.se.taskboard.business.domain.User;
import lombok.NoArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Mapper(componentModel = "spring")
@NoArgsConstructor
public abstract class UserDtoMapper {

    public abstract UserDto fromBusiness(User source);

    @Mapping(target = "createdAt", expression = "java(mapTimestamp(source.getCreatedAt()))")
    public abstract User toBusiness(UserDto source);

    protected LocalDateTime mapTimestamp(LocalDateTime timestamp) {
        if (timestamp == null) {
            return LocalDateTime.now(ZoneId.of("UTC"));
        }
        return timestamp;
    }
}

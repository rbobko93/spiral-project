package com.rbobko.spiralproject.mapper;

import com.rbobko.spiralproject.model.Status;
import com.rbobko.spiralproject.model.dto.StatusUpdateCard;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface StatusMapper {

    @Mapping(source = "iconUrl", target = "icon")
    @Mapping(source = "buttonText", target = "button")
    StatusUpdateCard toCard(Status status);

}

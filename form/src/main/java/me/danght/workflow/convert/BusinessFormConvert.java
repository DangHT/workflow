package me.danght.workflow.convert;

import me.danght.workflow.dataobject.BusinessFormDO;
import me.danght.workflow.dto.BusinessFormDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BusinessFormConvert {
    BusinessFormConvert INSTANCE = Mappers.getMapper(BusinessFormConvert.class);

    @Mappings({})
    BusinessFormDTO convertDOToDTO(BusinessFormDO businessFormDO);

    @Mappings({})
    BusinessFormDO convertDTOToDO(BusinessFormDTO businessFormDTO);
}

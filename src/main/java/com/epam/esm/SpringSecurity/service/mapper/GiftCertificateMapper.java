package com.epam.esm.SpringSecurity.service.mapper;

import com.epam.esm.SpringSecurity.repository.entity.GiftCertificate;
import com.epam.esm.SpringSecurity.service.dto.GiftCertificateDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GiftCertificateMapper {

    GiftCertificate mapDtoToEntity(GiftCertificateDto giftCertificateDto);

    GiftCertificateDto mapEntityToDto(GiftCertificate giftCertificate);
}

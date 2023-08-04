package com.saga.ecommerce.infra.viacep;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saga.ecommerce.core.domain.Address;
import com.saga.ecommerce.core.external_services.AddressFinder;

@Service
public class AddressFinderViaCEP implements AddressFinder {

    private ViaCEPRequest viaCEPRequest;

    public AddressFinderViaCEP(@Autowired ViaCEPRequest viaCEPRequest) {
        this.viaCEPRequest = viaCEPRequest;
    }

    @Override
    public Address find(String cep, String number) {
        var viaCEPAddress = this.viaCEPRequest.getAddressFrom(cep);
        if (viaCEPAddress == null) return null;
        var address = new Address(cep, viaCEPAddress.getLocalidade(), viaCEPAddress.getUf(), number, viaCEPAddress.getBairro());
        return address;
    }
}

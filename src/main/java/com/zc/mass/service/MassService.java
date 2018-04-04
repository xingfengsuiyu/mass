package com.zc.mass.service;

import org.springframework.stereotype.Service;

import java.io.InputStream;


@Service
public interface MassService {

    void mass(InputStream inputStream);

}

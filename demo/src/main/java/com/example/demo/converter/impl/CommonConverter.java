package com.example.demo.converter.impl;

import java.lang.reflect.ParameterizedType;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.converter.IConverter;

public class CommonConverter<E, D> implements IConverter<E, D> {

	@Autowired
	private ModelMapper mapper;
	
	private Class<E> classEntity;
	private Class<D> classDTO;
	
	@SuppressWarnings("unchecked")
	public CommonConverter() {
		ParameterizedType parameterizedType = (ParameterizedType) this.getClass().getGenericSuperclass();
		classEntity = (Class<E>) parameterizedType.getActualTypeArguments()[0];
		classDTO = (Class<D>) parameterizedType.getActualTypeArguments()[1];
	}
	
	@Override
	public E convertToEntity(D d) {
		return (E) mapper.map(d, this.classEntity);
	}

	@Override
	public D convertToDTO(E e) {
		return mapper.map(e, this.classDTO);
	}

}

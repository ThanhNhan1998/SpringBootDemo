package com.example.demo.converter;

public interface IConverter<E, D> {

	E convertToEntity(D d);
	D convertToDTO(E e);
}

package com.model.factory;

public abstract class Factory {
   public  abstract <T extends Humen> T createHuman(Class<?> clazz);
}

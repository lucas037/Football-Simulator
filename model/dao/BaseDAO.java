package model.dao;

public interface BaseDAO<Entity> {
    public Entity[] obter(Entity[] e);
    public int obterTamanho();
}
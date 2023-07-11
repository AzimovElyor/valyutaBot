package org.valyuta.common;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseRepository<ID , ENTITY extends BaseEntity<ID>> implements Repository<ID,ENTITY> {
    public abstract String getFilePath();

    @Override
    public void add(ENTITY entity) {
        List<ENTITY> entities = readFile();
        entities.add(entity);
        writeFile(entities);
    }

    @Override
    public ENTITY findByID(ID id) {
     return   readFile().stream()
               .filter(entity -> entity.getId().equals(id))
               .findFirst().get();
    }

    @Override
    public void delete(ID id) {
        List<ENTITY> entities = readFile();
        entities.removeIf(entity -> entity.getId().equals(id));
        writeFile(entities);

    }

    @Override
    public List<ENTITY> getAll() {
        return readFile();
    }

    @Override
    public ENTITY save(ENTITY entity) {
            entity.setUpdated(LocalDateTime.now());
       delete(entity.getId());
      add(entity);
      return entity;
    }
    private List<ENTITY> readFile(){
    try(ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(getFilePath()))) {
        return  (List<ENTITY>)inputStream.readObject();
    } catch (IOException | ClassNotFoundException e) {
        return new ArrayList<>();

    }
    }
    private void writeFile(List<ENTITY> list){
        try(ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(getFilePath()))) {
            outputStream.writeObject(list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

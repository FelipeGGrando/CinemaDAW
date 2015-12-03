package br.edu.ifsul.dao.web;
import br.edu.ifsul.modelo.Filme;
import java.io.Serializable;
public class FilmeDAO<T> extends GenericDAO<Filme> implements Serializable {
    
    public FilmeDAO(){
        super();
        super.setPersistentClass(Filme.class);
        super.getListOrder().add(new Order("id", "ID", "="));
        super.getListOrder().add(new Order("titulo", "Título", "like"));
        super.setCurrentOrder(super.getListOrder().get(1));
        super.setFilter("");
        super.setConverterOrder(new ConverterOrder(super.getListOrder()));
        
    }
}

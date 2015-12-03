package br.edu.ifsul.converters;
import br.edu.ifsul.modelo.Ator;
import java.io.Serializable;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@FacesConverter(value = "converterAtor")
public class ConverterAtor implements Serializable, Converter {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        if (string == null || string.equals("Selecione um registro")) {
            return null;
        }
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("cinema_dawPU");
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Ator.class, Integer.parseInt(string));
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
            emf.close();
        }

    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if (o == null){
            return null;
        }
        Ator obj = (Ator) o;
        return obj.getId().toString();
    }

}

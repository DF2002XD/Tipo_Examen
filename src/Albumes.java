import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Albumes {
    private ArrayList<Album> album;

    public Albumes() {
        this.album = new ArrayList<>();
    }

    public boolean addAlbum(Album nuevo) {
        for (Album x : album) {
            if (x.getTitulo().equals(nuevo.getTitulo())) {
                return false;
            }
        }
        album.add(nuevo);
        Collections.sort(album, Comparator.comparing(album -> album.getArtista()));
        return true;
    }

    public Album buscarAlbum(String titulo) {
        for (Album a : album) {
            if (a.getTitulo().equalsIgnoreCase(titulo)) {
                return a;
            }
        }
        return null;
    }

    public boolean modificarAlbum(String tituloOriginal, Album albumModificado) {
        for (int i = 0; i < album.size(); i++) {
            if (album.get(i).getTitulo().equalsIgnoreCase(tituloOriginal)) {
                album.set(i, albumModificado);
                Collections.sort(album, Comparator.comparing(Album::getArtista));
                return true;
            }
        }
        return false;
    }

    public boolean eliminarAlbum(String titulo) {
        return album.removeIf(a -> a.getTitulo().equalsIgnoreCase(titulo));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Album a : album) {
            sb.append(a.toString()).append("\n");
        }
        return sb.toString();
    }

}
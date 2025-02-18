public class Album {
    private String titulo;
    private String artista;
    private int anio;
    private int numCanciones;

    public Album(String titulo, String artista, int anio, int numCanciones) {
        this.titulo = titulo;
        this.artista = artista;
        this.anio = anio;
        this.numCanciones = numCanciones;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public int getNumCanciones() {
        return numCanciones;
    }

    public void setNumCanciones(int numCanciones) {
        this.numCanciones = numCanciones;
    }

    @Override
    public String toString() {
        return "Album [titulo=" + getTitulo() + ", artista=" + getArtista() + ", anio=" + getAnio() + ", numCanciones="
                + getNumCanciones()
                + "]";
    }
}

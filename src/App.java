import java.util.Scanner;

public class App {
    private static Scanner sc = new Scanner(System.in);
    private static Albumes albums = new Albumes();

    public static void main(String[] args) {
        int opcion;
        do {
            mostrarMenu();
            System.out.print("\nSeleccione una opción: ");
            opcion = sc.nextInt();
            sc.nextLine(); // Consumir el salto de línea
            switch (opcion) {
                case 1:
                    mostrarAlbumes();
                    break;
                case 2:
                    agregarAlbum();
                    break;
                case 3:
                    modificarAlbum();
                    break;
                case 4:
                    eliminarAlbum();
                    break;
                case 5:
                    System.out.println("Saliendo del programa...");
                    sc.close();
                    break;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        } while (opcion != 5);
    }

    private static void mostrarMenu() {
        System.out.println("\nGestionar y clasificar los álbumes musicales");
        System.out.println("1. Mostrar albumes");
        System.out.println("2. Agregar un nuevo album");
        System.out.println("3. Modificar un album");
        System.out.println("4. Eliminar un album");
        System.out.println("5. Salir");
    }

    private static void mostrarAlbumes() {
        System.out.println("\nLista de álbumes:");
        System.out.println(albums);
    }

    private static void agregarAlbum() {
        boolean datosCorrectos = false;
        String titulo = "";
        String artista = "";
        int anio = 0, numCanciones = 0;

        while (!datosCorrectos) {
            try {
                System.out.print("Ingrese el título del álbum: ");
                titulo = sc.nextLine();
                if (titulo.isBlank()) {
                    System.out.println("El título no puede estar vacío.");
                    continue;
                }
                System.out.print("Ingrese el nombre del artista: ");
                artista = sc.nextLine();
                if (artista.isBlank()) {
                    System.out.println("El nombre de artista no puede estar vacío.");
                    continue;
                }
                System.out.print("Ingrese el año del álbum: ");
                anio = Integer.parseInt(sc.nextLine());
                if (anio < 1800 || anio > 2026) {
                    System.err.println("El año debe estar entre 1800 y 2026");
                    continue;
                }
                System.out.print("Ingrese el numero de canciones del álbum: ");
                numCanciones = Integer.parseInt(sc.nextLine());
                if (numCanciones < 1) {
                    System.err.println("El número de canciones debe ser un valor positivo.");
                    continue;
                }
                datosCorrectos = true;
            } catch (NumberFormatException e) {
                System.err.println("Error: Ingrese un número válido para el año y la cantidad de canciones.");
            }
        }
        Album nuevo = new Album(titulo, artista, anio, numCanciones);

        if (albums.addAlbum(nuevo)) {
            System.out.println("El album se ha agregado correctamente.");
        } else {
            System.err.println("Error al agregar el álbum. El título ya existe en la colección.");
        }
    }

    private static void modificarAlbum() {
        System.out.println("Modificar un álbum");
        System.out.print("Ingrese el título del álbum a modificar: ");
        String tituloOriginal = sc.nextLine();

        Album albumAModificar = albums.buscarAlbum(tituloOriginal);
        if (albumAModificar == null) {
            System.out.println("El álbum no se encuentra en la colección.");
            return;
        }

        Album albumModificado = new Album(albumAModificar.getTitulo(), albumAModificar.getArtista(),
                albumAModificar.getAnio(), albumAModificar.getNumCanciones());

        int opcion;
        do { 
            System.out.println("\nÁlbum actual:");
            System.out.println(albumModificado);
            
            System.out.println("\nQué desea modificar?");
            System.out.println("1. Título");
            System.out.println("2. Artista");
            System.out.println("3. Año");
            System.out.println("4. Número de canciones");
            System.out.println("5. Terminar modificación");
            System.out.print("Seleccione una opción: ");
            opcion = Integer.parseInt(sc.nextLine());

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese el nuevo título: ");
                    String nuevoTitulo = sc.nextLine();
                    if (!nuevoTitulo.isBlank()) {
                        albumModificado.setTitulo(nuevoTitulo);
                    } else {
                        System.out.println("El título no puede estar vacío.");
                    }
                    break;
                case 2:
                    System.out.print("Ingrese el nuevo artista: ");
                    String nuevoArtista = sc.nextLine();
                    if (!nuevoArtista.isBlank()) {
                        albumModificado.setArtista(nuevoArtista);
                    } else {
                        System.out.println("El nombre del artista no puede estar vacío.");
                    }
                    break;
                case 3:
                    System.out.print("Ingrese el nuevo año: ");
                    try {
                        int nuevoAnio = Integer.parseInt(sc.nextLine());
                        if (nuevoAnio >= 1800 && nuevoAnio <= 2026) {
                            albumModificado.setAnio(nuevoAnio);
                        } else {
                            System.out.println("El año debe estar entre 1800 y 2026.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Por favor, ingrese un año válido.");
                    }
                    break;
                case 4:
                    System.out.print("Ingrese el nuevo número de canciones: ");
                    try {
                        int nuevoNumCanciones = Integer.parseInt(sc.nextLine());
                        if (nuevoNumCanciones > 0) {
                            albumModificado.setNumCanciones(nuevoNumCanciones);
                        } else {
                            System.out.println("El número de canciones debe ser positivo.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Por favor, ingrese un número válido.");
                    }
                    break;
                case 5:
                    System.out.println("Terminando modificación...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 5);

        if (albums.modificarAlbum(tituloOriginal, albumModificado)) {
            System.out.println("Álbum modificado correctamente.");
        } else {
            System.out.println("No se pudo modificar el álbum.");
        }
    }

    private static void eliminarAlbum() {
        System.out.println("Eliminar un álbum");
        System.out.print("Ingrese el título del álbum a eliminar: ");
        String titulo = sc.nextLine();

        if (albums.eliminarAlbum(titulo)) {
            System.out.println("El álbum ha sido eliminado correctamente.");
        } else {
            System.out.println("No se encontró un álbum con ese título.");
        }
    }
}

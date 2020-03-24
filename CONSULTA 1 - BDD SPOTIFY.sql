 -- Consulta 1
 --  Mostrar el nombre del album de las canciones que empiecen con 'S'.
 use spotify;
Select
albums.title
from 
albums
Join songs on albums.id = songs.album
where songs.title LIKE 'S%';
-- Consulta 2
-- Contar todas las canciones del genero Hip-Hop, Pop y Rap
use spotify;
select
COUNT(songs.id) as contador  
from
songs
join genres on songs.genre = genres.id
where genres.name = 'Hip-hop'
OR genres.name = 'Pop' 
OR  genres.name = 'Rap' ;
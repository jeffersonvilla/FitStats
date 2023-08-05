delimiter $$ 

create trigger verificando_detalles_caminata_carrera 
before insert on detalles_caminata_carrera 
for each row begin
declare x varchar(50);
set x = (select nombre_actividad from tipo_actividad t 
		join actividad a on t.tipo_actividad_id = a.tipo_actividad_id 
        where a.actividad_id = new.actividad_id);
if x != 'Caminar/correr' then 
signal sqlstate '45000' 
set MESSAGE_TEXT = "Solo se pueden ingresar actividades de tipo 'Caminar/correr'"; 
end if; 
end;$$ 

create trigger verificando_detalles_ciclismo 
before insert on detalles_ciclismo
for each row begin
declare x varchar(50);
set x = (select nombre_actividad from tipo_actividad t 
		join actividad a on t.tipo_actividad_id = a.tipo_actividad_id 
        where a.actividad_id = new.actividad_id);
if x != 'Ciclismo' then 
signal sqlstate '45000' 
set MESSAGE_TEXT = "Solo se pueden ingresar actividades de tipo 'Ciclismo'"; 
end if; 
end;$$ 

create trigger verificando_detalles_natacion 
before insert on detalles_natacion
for each row begin
declare x varchar(50);
set x = (select nombre_actividad from tipo_actividad t 
		join actividad a on t.tipo_actividad_id = a.tipo_actividad_id 
        where a.actividad_id = new.actividad_id);
if x != 'Natacion' then 
signal sqlstate '45000' 
set MESSAGE_TEXT = "Solo se pueden ingresar actividades de tipo 'Natacion'"; 
end if; 
end;$$

create trigger verificando_detalles_deportes_equipo
before insert on detalles_deporte_equipo
for each row begin
declare x varchar(50);
set x = (select nombre_actividad from tipo_actividad t 
		join actividad a on t.tipo_actividad_id = a.tipo_actividad_id 
        where a.actividad_id = new.actividad_id);
if x != 'Deportes de equipo' then 
signal sqlstate '45000' 
set MESSAGE_TEXT = "Solo se pueden ingresar actividades de tipo 'Deportes de equipo'"; 
end if; 
end;$$ 

create trigger verificando_detalles_entrenamiento
before insert on detalles_entrenamiento
for each row begin
declare x varchar(50);
set x = (select nombre_actividad from tipo_actividad t 
		join actividad a on t.tipo_actividad_id = a.tipo_actividad_id 
        where a.actividad_id = new.actividad_id);
if x != 'Entrenamiento en el Gimnasio' then 
signal sqlstate '45000' 
set MESSAGE_TEXT = "Solo se pueden ingresar actividades de tipo 'Entrenamiento en el Gimnasio'"; 
end if; 
end;$$  

create trigger verificando_detalles_yoga_estiramientos
before insert on detalles_yoga_estiramientos
for each row begin
declare x varchar(50);
set x = (select nombre_actividad from tipo_actividad t 
		join actividad a on t.tipo_actividad_id = a.tipo_actividad_id 
        where a.actividad_id = new.actividad_id);
if x != 'Yoga/estiramientos/pilates' then 
signal sqlstate '45000' 
set MESSAGE_TEXT = "Solo se pueden ingresar actividades de tipo 'Yoga/estiramientos/pilates'"; 
end if; 
end;$$  

create trigger verificando_detalles_otra_actividad
before insert on detalles_otra_actividad
for each row begin
declare x varchar(50);
set x = (select nombre_actividad from tipo_actividad t 
		join actividad a on t.tipo_actividad_id = a.tipo_actividad_id 
        where a.actividad_id = new.actividad_id);
if x != 'Otra actividad' then 
signal sqlstate '45000' 
set MESSAGE_TEXT = "Solo se pueden ingresar actividades de tipo 'Otra actividad'"; 
end if; 
end;$$
delimiter ;
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
delimiter ;
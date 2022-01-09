insert into users(userId, password, firstName, lastName)
 values ('eschuler', 'pass1234', 'Elena', 'Schuler');

insert into users(userId, password, firstName, lastName)
 values ('mmuster', 'pass1234', 'Maxime', 'Muster');

  insert into songs (id, title,artist,label,released)
  values(1,'Sussudio','Phil Collins','Virgin',1985),
  (2,'Muskrat Love','Captain and Tennille','A&M',1976),
  (3,'Afternoon Delight','Starland Vocal Band','Windsong',1976),
  (4,'MacArthur Park','Richard Harris','Dunhill Records',1968);


  insert into songList (id,ownerId,isPrivate, name)
  values(1,'mmuster', true, 'MaximPrivateList'),
  (2,'mmuster',false, 'MaximeNotPrivate'),
  (3,'eschuler', true, 'ElenaPrivateList'),
  (4,'eschuler', false, 'ElenaPublicList');

  insert into song_songlist (id, songlist_id, song_id)
  values(1,1,1),(2,1,2),(3,2,3),(4,2,4);




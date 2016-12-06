MATCH (n) DETACH DELETE n

CREATE (Ad1:Address {city: 'Szekszard', country: 'Magyarorszag', address: 'Hargitai utca 2'})
CREATE (A:User {name: 'Andras', email: 'andras1@gmail.com', hasNotification: false, hasWarning: false, isAdmin: false, deleted: false, password: '42cc48a7dc3274de66d5a7f4e27c43f728c9abd8', salt: '00D4454C92F295EC2A47C153DCF78FAD', age: 34, phoneNumber: '+36301234567', introduction: 'Andras vagyok', country: 'Magyarorszag', city: 'Szekszard'})
CREATE (t1:SportEvent {title:'Tennis event 1', date:'2016-08-18 10:00:00', levelFrom: 4, levelTo: 6, isOpened: true, isPublic: true, type: 'TENNIS', description: 'tenisz', maxSize: 2})
CREATE 
    (A)-[:OWNER]->(t1),
    (A)-[:MEMBER]->(t1),
    (t1)-[:ADDRESS]->(Ad1)    
CREATE (t2:SportEvent {title:'Tennis event 2', date:'2016-08-19 16:00:00', levelFrom: 4, levelTo: 6, isOpened: true, isPublic: true, type: 'TENNIS', description: 'tenisz', maxSize: 4})
CREATE 
    (A)-[:OWNER]->(t2),
    (A)-[:MEMBER]->(t2),
    (t2)-[:ADDRESS]->(Ad1)    
CREATE (D:User {name: 'David', email: 'david4@gmail.com', hasNotification: false, hasWarning: false, isAdmin: false, deleted: false, password: 'dec55448cbd8acd06fce8c017d2f67989237ab2b', salt: '536FABC95D5C6CC8634337C7ADA8BBE7', age: 27, phoneNumber: '+36301234567', introduction: 'David vagyok', country: 'Magyarorszag', city: 'Szekszard'})
CREATE
    (t1)-[:COMMENT {message: 'Labdát hozol?', date: '2016-10-18 10:44:00'}]->(D),
    (t1)-[:COMMENT {message: 'Igen', date: '2016-10-18 13:44:12'}]->(A)
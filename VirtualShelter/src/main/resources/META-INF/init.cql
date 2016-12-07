MATCH (n) DETACH DELETE n

CREATE (Ad1:Address {city: 'Szekszard', country: 'HUN', address: 'Hargitai utca 2'})
CREATE (A:User {name: 'Andras', email: 'andras1@gmail.com', hasNotification: true, hasWarning: false, isAdmin: false, deleted: false, password: '42cc48a7dc3274de66d5a7f4e27c43f728c9abd8', salt: '00D4454C92F295EC2A47C153DCF78FAD', age: 34, phoneNumber: '+36301234567', introduction: 'Andras vagyok', country: 'HUN', city: 'Szekszard'})
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
CREATE (B:User {name: 'Bela', email: 'bela2@gmail.com', hasNotification: false, hasWarning: false, isAdmin: false, deleted: false, password: '7747a1955b6609dce457630a73b535f26895756f', salt: '86986EAE6B5308814EA045A65C622E9E', age: 23, phoneNumber: '+363063636326', introduction: 'Bela vagyok', country: 'HUN', city: 'Kaposvar'})
CREATE (Ad2:Address {city: 'Kaposvar', country: 'HUN', address: 'Iszak utca'})
CREATE (f1:SportEvent {title:'Football event', date:'2016-07-13 16:00:00', levelFrom: 8, levelTo: 8, isOpened: true, isPublic: true, type: 'FOOTBALL', description: 'foci', maxSize: 11})
CREATE 
    (B)-[:OWNER]->(f1),
    (B)-[:MEMBER]->(f1),
    (B)-[:COMMENTED {message: 'Lehet esni fog?', date: '2016-11-03 14:44:00'}]->(f1),
    (f1)-[:ADDRESS]->(Ad2)
CREATE (D:User {name: 'David', email: 'david4@gmail.com', hasNotification: true, hasWarning: false, isAdmin: false, deleted: false, password: 'dec55448cbd8acd06fce8c017d2f67989237ab2b', salt: '536FABC95D5C6CC8634337C7ADA8BBE7', age: 27, phoneNumber: '+36306843836', introduction: 'David vagyok', country: 'HUN', city: 'Szekszard'})
CREATE (C:User {name: 'Cecil', email: 'cecil3@gmail.com', hasNotification: true, hasWarning: false, isAdmin: false, deleted: false, password: '2170be9ab68f3917e411a94fe0f10869f4ad5429', salt: '559AE3DB9DDBCD02E7B7A0012ED29CAB', age: 27, phoneNumber: '+36308257274', introduction: 'Cecil vagyok', country: 'HUN', city: 'Pecs'})
CREATE (E:User {name: 'Elemer', email: 'elemer5@gmail.com', hasNotification: false, hasWarning: false, isAdmin: false, deleted: false, password: '2fde8a81f558e798ea4eca651269505e67d6b460', salt: '6DE750806F582756B772432FEA1512C8', age: 12, phoneNumber: '+36303423423', introduction: 'Cecil vagyok', country: 'HUN', city: 'Pecs'})
CREATE (F:User {name: 'Ferenc', email: 'ferenc6@gmail.com', hasNotification: true, hasWarning: true, isAdmin: false, deleted: false, password: '0535c92335763c29cfff14a3944d655641dffc3a', salt: '1E63AD9C52FEDD735D3E1D461C92C74C', age: 44, phoneNumber: '+36308543632', introduction: 'Ferenc vagyok', country: 'HUN', city: 'Kecskemet'})
CREATE (G:User {name: 'Gabor', email: 'gabor7@gmail.com', hasNotification: false, hasWarning: false, isAdmin: false, deleted: false, password: '926fdd0d52c83023f68b08f22296769ff09a57de', salt: 'A7E313F0000419D5887BE0916C003D36', age: 36, phoneNumber: '+36306363632', introduction: 'Gabor vagyok', country: 'HUN', city: 'Miskolc'})
CREATE (k1:SportEvent {title:'kezikezijeee', date:'2016-09-15 16:00:00', levelFrom: 3, levelTo: 7, isOpened: true, isPublic: true, type: 'HANDBALL', description: 'kezi', maxSize: 8})
CREATE (f2:SportEvent {title:'focicsumicsa', date:'2016-11-16 16:00:00', levelFrom: 1, levelTo: 10, isOpened: false, isPublic: true, type: 'FOOTBALL', description: 'focijeee', maxSize: 3})
CREATE
    (D)-[:COMMENTED {message: 'Labdát hozol?', date: '2016-10-18 10:44:00'}]->(t1),
    (A)-[:COMMENTED {message: 'Igen', date: '2016-10-18 13:44:12'}]->(t1),
    (D)-[:OWNER]->(k1),
    (G)-[:MEMBER]->(k1),
    (D)-[:MEMBER]->(k1),
    (k1)-[:ADDRESS]->(Ad2),
    (D)-[:OWNER]->(f2),
    (D)-[:MEMBER]->(f2),
    (F)-[:MEMBER]->(f2),
    (A)-[:MEMBER]->(f2),
    (f2)-[:ADDRESS]->(Ad2),
    (A)-[:FRIEND {listenToNotifications: true}]->(B),
    (A)-[:FRIEND {listenToNotifications: true}]->(D),
    (A)-[:FRIEND {listenToNotifications: false}]->(E),
    (B)-[:FRIEND {listenToNotifications: true}]->(A),
    (D)-[:FRIEND {listenToNotifications: true}]->(A),
    (E)-[:FRIEND {listenToNotifications: true}]->(A),
    (G)-[:FRIEND {listenToNotifications: true}]->(F),
    (F)-[:FRIEND {listenToNotifications: true}]->(G)
CREATE (c1:Conversation {isActive: true, lastMessage: 'Mizu?', lastSendTime: '2016-08-20 16:00:00'})
CREATE
    (A)-[:MEMBER]->(c1),
    (B)-[:MEMBER]->(c1),
    (A)-[:WROTE_TO {message: 'Csa', sendTime:'2016-08-18 10:00:00'}]->(c1),
    (B)-[:WROTE_TO {message: 'Csa', sendTime:'2016-08-18 12:00:00'}]->(c1),
    (A)-[:WROTE_TO {message: 'Mizu?', sendTime:'2016-08-20 16:00:00'}]->(c1)
CREATE (c2:Conversation {isActive: true, lastMessage: 'Szeva', lastSendTime: '2016-08-18 14:00:00'})
CREATE
    (A)-[:MEMBER]->(c2),
    (C)-[:MEMBER]->(c2),
    (A)-[:WROTE_TO {message: 'Szeva', sendTime:'2016-08-18 14:00:00'}]->(c2)
    
CREATE (n1:Notification {isDeclined: false, sendTime: '2016-11-12 18:10:45', type: 'FRIEND_REQUEST'})
CREATE
    (n1)-[:OWNER]->(A),
    (F)-[:SENT]->(n1),
    (F)-[:FRIEND_REQUEST]->(A)
CREATE (n2:Notification {isDeclined: false, sendTime: '2016-11-12 18:10:45', type: 'FRIEND_REQUEST'})
CREATE
    (n2)-[:OWNER]->(C),
    (A)-[:SENT]->(n2),
    (A)-[:FRIEND_REQUEST]->(C)
CREATE (n3:Notification {isDeclined: false, sendTime: '2016-11-13 19:10:45', type: 'EVENT_REQUEST'})
CREATE
    (n3)-[:OWNER]->(D),
    (A)-[:SENT]->(n3),
    (n3)-[:NEWS]->(k1),
    (A)-[:APPLIED]->(k1)
CREATE (n4:Notification {isDeclined: false, sendTime: '2016-11-13 19:10:45', type: 'EVENT_REQUEST'})
CREATE
    (n4)-[:OWNER]->(B),
    (E)-[:SENT]->(n4),
    (n4)-[:NEWS]->(f1),
    (E)-[:APPLIED]->(f1)
CREATE (r1:Rating {sport: 'TENNIS', rateNumbers: 3, sumValue: 23})
CREATE
    (A)-[:RATING]->(r1)
CREATE (r2:Rating {sport: 'HANDBALL', rateNumbers: 0, sumValue: 0})
CREATE
    (A)-[:RATING]->(r2)
CREATE (r3:Rating {sport: 'FOOTBALL', rateNumbers: 1, sumValue: 5})
CREATE
    (A)-[:RATING]->(r3)
CREATE (r4:Rating {sport: 'FOOTBALL', rateNumbers: 1, sumValue: 3})
CREATE
    (D)-[:RATING]->(r4)


create database star;
\c star;

create sequence ComposantSeq start with 1;
create sequence CompositionSeq start with 1;

drop sequence ComposantSeq;
drop sequence CompositionSeq;


create table Composant(
    idComposant varchar(15) Primary key,
    nomComposant varchar(15),
    Quantite decimal(10,2),
    Prix decimal(10,2),
    primaire varchar(7),
    unite varchar(10)
);

create table Composition(
    idComposition varchar(20) Primary key,
    idCompose varchar(15),
    idComposant varchar(15),
    QPrincipale decimal(10,2),
    QDerive decimal(10,2),
    Foreign key (idCompose) references Composant(idComposant),
    Foreign key (idComposant) references Composant(idComposant)
);



insert into Composant values(concat('Composant0000',ComposantSeq.nextVal),'eau',15,30000,'true','Litre');
insert into Composant values(concat('Composant0000',ComposantSeq.nextVal),'gaz',5,23000,'true','Kg');
insert into Composant values(concat('Composant0000',ComposantSeq.nextVal),'eau gazeuse',0,0,'false','Litre');
insert into Composant values(concat('Composant0000',ComposantSeq.nextVal),'arome',2,23000,'true','Sachet');
insert into Composant values(concat('Composant0000',ComposantSeq.nextVal),'sucre',20,24000,'true','Kg');
insert into Composant values(concat('Composant0000',ComposantSeq.nextVal),'conservateur',20,45000,'true','Sachet');
insert into Composant values(concat('Composant0000',ComposantSeq.nextVal),'colorant',17,23470,'true','Sachet');
insert into Composant values(concat('Composant0000',ComposantSeq.nextVal),'alcool',20,12345,'true','Litre');
insert into Composant values(concat('Composant0000',ComposantSeq.nextVal),'orge',0,0,'false','Litre');
insert into Composant values(concat('Composant000',ComposantSeq.nextVal),'levure',0,0,'false','Kg');
insert into Composant values(concat('Composant000',ComposantSeq.nextVal),'mais',23,15000,'true','Kg');
insert into Composant values(concat('Composant000',ComposantSeq.nextVal),'farine',23,17000,'true','Kg');
insert into Composant values(concat('Composant000',ComposantSeq.nextVal),'Limonade',0,0,'false','piece');
insert into Composant values(concat('Composant000',ComposantSeq.nextVal),'Fanta Orange',0,0,'false','piece');
insert into Composant values(concat('Composant000',ComposantSeq.nextVal),'Biere',0,0,'false','piece');

insert into Composition values(concat('Composition0000',CompositionSeq.nextVal),'Composant00003','Composant00001',1,0.95);
insert into Composition values(concat('Composition0000',CompositionSeq.nextVal),'Composant00003','Composant00002',1,0.05);
insert into Composition values(concat('Composition0000',CompositionSeq.nextVal),'Composant00010','Composant00005',1,0.75);
insert into Composition values(concat('Composition0000',CompositionSeq.nextVal),'Composant00010','Composant00012',1,0.85);
insert into Composition values(concat('Composition0000',CompositionSeq.nextVal),'Composant00009','Composant00010',1,0.75);
insert into Composition values(concat('Composition0000',CompositionSeq.nextVal),'Composant00009','Composant00011',1,1.3);
insert into Composition values(concat('Composition0000',CompositionSeq.nextVal),'Composant00009','Composant00001',1,1.2);

insert into Composition values(concat('Composition0000',CompositionSeq.nextVal),'Composant00013','Composant00003',1,1);
insert into Composition values(concat('Composition0000',CompositionSeq.nextVal),'Composant00013','Composant00005',1,0.6);
insert into Composition values(concat('Composition000',CompositionSeq.nextVal),'Composant00013','Composant00004',1,2);

insert into Composition values(concat('Composition000',CompositionSeq.nextVal),'Composant00014','Composant00003',1,1);
insert into Composition values(concat('Composition000',CompositionSeq.nextVal),'Composant00014','Composant00005',1,1.2);
insert into Composition values(concat('Composition000',CompositionSeq.nextVal),'Composant00014','Composant00007',1,5);


insert into Composition values(concat('Composition000',CompositionSeq.nextVal),'Composant00015','Composant00003',1,0.75);
insert into Composition values(concat('Composition000',CompositionSeq.nextVal),'Composant00015','Composant00008',1,0.235);
insert into Composition values(concat('Composition000',CompositionSeq.nextVal),'Composant00015','Composant00006',1,2);
insert into Composition values(concat('Composition000',CompositionSeq.nextVal),'Composant00015','Composant00009',1,0.25);



select distinct idCompose, idComposant from Composition
    start with
    connect by prior idComposant=idCompose
    order by idCompose;

-- select Composition.idCompose,Composition.idComposant,Composant.nomComposant, Composant.Prix from Composition 
--     Join Composant On composition.idCompose= composant.idComposant
--     connect by prior composition.idComposant=composition.idCompose
--     order by composition.idCompose;

SELECT e1.name AS employee, e2.name AS manager
FROM emp e1
JOIN emp e2 ON e1.mgr = e2.empno
START WITH e1.mgr IS NULL
CONNECT BY PRIOR e1.empno = e1.mgr
ORDER BY e1.empno

select emp.empno, emp.ename, emp.mgr from emp
    START with emp.mgr=7566
    CONNECT BY PRIOR emp.empno= emp.mgr;
    -- ORDER BY emp.empno;

select *  from composition
    START with idCompose='Composant00003'
    CONNECT BY PRIOR idComposant= idCompose;

orge
    levure
        farine
        sucre
    mais
    eau
alcool
eau gazeuse
conservateur
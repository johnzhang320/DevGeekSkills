   We can create any number of tables in one database scheme, when we test one relationship such one to many , we only create
   session factory for the one to many tables without including many to many or one to one table. this make sense on unit test in
   in large project because
   in one scheme , we may hundreds of tables, when we focus our new feature of tables, we must avoid disaster caused by 
   hibernate.hbm2ddl.auto which could damage other data or tables
   I created the abstract sessionfactory to allow user separately create sessionfactory only based on bunch of tables current they concern
   
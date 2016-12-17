# !/bin/sh
rootPath=/root
cd $rootPath/works/book-club

git pull

echo "mvn install"
mvn clean package -Dmaven.test.skip -pl web -am -e -U

echo "book-club module"
if [ -f $rootPath/projects/club/book-club.jar ];
then
    if [ -f $rootPath/projects/club/book-club.jar.bak ];
    then
        echo "remove book-club bak"
            rm -rf $rootPath/projects/club/book-club.jar.bak
    fi;
    echo "bak book-club.jar"
    mv $rootPath/projects/club/book-club.jar $rootPath/projects/club/book-club.jar.bak
fi;
echo "cp book-club to project"
cp ./web/target/book-club.jar $rootPath/projects/club
if [ -f /etc/init.d/book-club ];
then
    echo "restart book-club "
    service club restart
else
    sudo ln -s /root/projects/book-club/book-club.jar /etc/init.d/club
    mkdir /var/log/book-club
    echo "MODE=service\r\nJAVA_OPTS=\"-Xms512m -Xmx512m -XX:PermSize=256M -Dspring.profiles.active=test\"\r\nLOG_FOLDER=/var/log/book-club\r\nLOG_FILENAME=book-club.log" > /root/projects/book-club/book-club.conf
    echo "start book-club "
    service club start
fi;

#end

# !/bin/sh
set -e
rootPath=/root
cd $rootPath/works/user-center

git pull

echo "mvn install"
mvn clean package -Dmaven.test.skip -pl web -am -e -U

echo "user-center module"
if [ -f $rootPath/projects/user-center/user-center.jar ];
then
    if [ -f $rootPath/projects/user-center/user-center.jar.bak ];
    then
        echo "remove user-center bak"
            rm -rf $rootPath/projects/user-center/user-center.jar.bak
    fi;
    echo "bak user-center.jar"
    mv $rootPath/projects/user-center/user-center.jar $rootPath/projects/user-center/user-center.jar.bak
fi;
echo "cp user-center to project"
cp ./web/target/user-center.jar $rootPath/projects/user-center/
if [ -f /etc/init.d/club ];
then
    echo "restart user-center "
    service user-center restart
    tail -f /var/log/user-center/user-center.log

else
    sudo ln -s /root/projects/user-center/user-center.jar /etc/init.d/user-center
    chmod 751 /etc/init.d/user-center
    mkdir /var/log/user-center
    echo "MODE=service\\r\\nJAVA_OPTS=\"-Xms512m -Xmx512m -XX:PermSize=256M -Dspring.profiles.active=test\"\\r\\nLOG_FOLDER=/var/log/user-center\\r\\nLOG_FILENAME=user-center.log" > /root/projects/user-center/user-center.conf
    echo "start user-center "
    service user-center start
    tail -f /var/log/user-center/user-center.log
fi;

#end

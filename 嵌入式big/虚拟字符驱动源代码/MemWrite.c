#include <stdio.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>

int main()
{
    int fd = 0;
    int src0[] = {0, 1, 2, 3, 4};
    int src1[] = {10, 11, 12, 13, 14};
    
    /*���豸�ļ�*/
    fd = open("/dev/memdev0", O_RDWR);
    
    /*д������*/
    write(fd, src0, sizeof(src0));
    
    /*�ر��豸*/
    close(fd);
    
    fd = open("/dev/memdev1", O_RDWR);
    
    /*д������*/
    write(fd, src1, sizeof(src1));
    
    /*�ر��豸*/
    close(fd);
    
    return 0;    
}
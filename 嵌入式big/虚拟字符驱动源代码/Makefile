
ifneq ($(KERNELRELEASE),)
    obj-m := MemDev.o#obj-m 指编译成外部模块
else
    KERNELDIR := /usr/src/linux-headers-3.13.0-32-generic #定义一个变量,指向内核目录
    PWD := $(shell pwd)
modules:
    $(MAKE) -C $(KERNELDIR) M=$(PWD) modules
endif
clean:
    $(MAKE) -C $(KERNELDIR) M=$(PWD) clean
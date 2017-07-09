TARGETS = console-setup alsa-utils resolvconf mountkernfs.sh ufw pppd-dns hostname.sh plymouth-log x11-common apparmor lm-sensors udev keyboard-setup mountdevsubfs.sh brltty procps hwclock.sh checkroot.sh networking urandom bootmisc.sh mountall.sh checkfs.sh checkroot-bootclean.sh mountnfs.sh mountall-bootclean.sh kmod mountnfs-bootclean.sh
INTERACTIVE = console-setup udev keyboard-setup checkroot.sh checkfs.sh
udev: mountkernfs.sh
keyboard-setup: mountkernfs.sh udev
mountdevsubfs.sh: mountkernfs.sh udev
brltty: mountkernfs.sh udev
procps: mountkernfs.sh udev
hwclock.sh: mountdevsubfs.sh
checkroot.sh: hwclock.sh mountdevsubfs.sh hostname.sh keyboard-setup
networking: resolvconf mountkernfs.sh urandom procps
urandom: hwclock.sh
bootmisc.sh: udev mountall-bootclean.sh checkroot-bootclean.sh mountnfs-bootclean.sh
mountall.sh: checkfs.sh checkroot-bootclean.sh
checkfs.sh: checkroot.sh
checkroot-bootclean.sh: checkroot.sh
mountnfs.sh: networking
mountall-bootclean.sh: mountall.sh
kmod: checkroot.sh
mountnfs-bootclean.sh: mountnfs.sh

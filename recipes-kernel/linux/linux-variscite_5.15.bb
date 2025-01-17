# Copyright (C) 2013-2016 Freescale Semiconductor
# Copyright 2017 NXP
# Copyright 2018-2020 Variscite Ltd.
# Released under the MIT license (see COPYING.MIT for the terms)

SUMMARY = "Linux kernel provided and supported by Variscite"
DESCRIPTION = "Linux kernel provided and supported by Variscite (based on the kernel provided by NXP) \
with focus on i.MX Family SOMs. It includes support for many IPs such as GPU, VPU and IPU."

require recipes-kernel/linux/linux-imx.inc
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

FILES:${KERNEL_PACKAGE_NAME}-base += "${nonarch_base_libdir}/modules/${KERNEL_VERSION}/modules.builtin.modinfo "

DEPENDS += "lzop-native bc-native"

# Don't include kernels in standard images
RRECOMMENDS:${KERNEL_PACKAGE_NAME}-base = ""

DEFAULT_PREFERENCE = "1"

KERNEL_SRC ?= "git://github.com/varigit/linux-imx;protocol=https"

SRCBRANCH = "5.15-2.0.x-imx_var01"
SRCREV = "740e6c7a7b0972255c11686d4041ad629ab3f361"
LINUX_VERSION = "5.15.60"

SRCBRANCH:var-som-mx6 = "lf-5.15.y_var01"
SRCREV:var-som-mx6 = "042583ccf25a7702b024c9fc7789279b61ba0632"
LINUX_VERSION:var-som-mx6 = "5.15.71"

SRCBRANCH:imx6ul-var-dart = "lf-5.15.y_var01"
SRCREV:imx6ul-var-dart = "848569c825d3c545ed857778b48325e61b0a9a0d"
LINUX_VERSION:imx6ul-var-dart = "5.15.71"

SRCBRANCH:imx7-var-som = "lf-5.15.y_var01"
SRCREV:imx7-var-som = "ed54a5eb79177bbde3d359f92c1bf1fb7fdb1f20"
LINUX_VERSION:imx7-var-som = "5.15.71"

SRCBRANCH:imx8mn-var-som = "lf-5.15.y_var01"
SRCREV:imx8mn-var-som = "da2218c723da2323ae744b8ba71a93802a23f976"
LINUX_VERSION:imx8mn-var-som = "5.15.71"

SRCBRANCH:imx8mp-var-dart = "lf-5.15.y_var01"
SRCREV:imx8mp-var-dart = "7d95b5611171d1a973b6e8d4bd65348fd982de44"
LINUX_VERSION:imx8mp-var-dart = "5.15.71"

SRCBRANCH:imx93-var-som = "lf-5.15.y_var01"
SRCREV:imx93-var-som = "da2218c723da2323ae744b8ba71a93802a23f976"
LINUX_VERSION:imx93-var-som = "5.15.71"

SRC_URI = "${KERNEL_SRC};branch=${SRCBRANCH}"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

LOCALVERSION:var-som-mx6 = "-imx6"
LOCALVERSION:imx6ul-var-dart = "-imx6ul"
LOCALVERSION:imx7-var-som = "-imx7"
LOCALVERSION:imx8mp-var-dart = "-imx8mp"
LOCALVERSION:imx8mq-var-dart = "-imx8mq"
LOCALVERSION:imx8mm-var-dart = "-imx8mm"
LOCALVERSION:imx8mn-var-som = "-imx8mn"
LOCALVERSION:imx8qxp-var-som = "-imx8x"
LOCALVERSION:imx8qxpb0-var-som = "-imx8x"
LOCALVERSION:imx8qm-var-som = "-imx8qm"
LOCALVERSION:imx93-var-som = "-imx93"

KBUILD_DEFCONFIG:mx6-nxp-bsp = "imx_v7_var_defconfig"
KBUILD_DEFCONFIG:mx7-nxp-bsp = "imx_v7_var_defconfig"
KBUILD_DEFCONFIG:mx8-nxp-bsp = "imx8_var_defconfig"
KBUILD_DEFCONFIG:mx9-nxp-bsp = "imx8_var_defconfig"
KBUILD_DEFCONFIG:imx8mq-var-dart = "imx8mq_var_dart_defconfig"
DEFAULT_DTB:imx8mq-var-dart = "sd-lvds"
DEFAULT_DTB:imx8qxp-var-som = "sd"
DEFAULT_DTB:imx8qxpb0-var-som = "sd"
DEFAULT_DTB:imx8qm-var-som = "lvds"
DEFAULT_DTB_PREFIX:imx8mq-var-dart = "imx8mq-var-dart-dt8mcustomboard"
DEFAULT_DTB_PREFIX:imx8qxp-var-som = "imx8qxp-var-som-symphony"
DEFAULT_DTB_PREFIX:imx8qxpb0-var-som = "imx8qxp-var-som-symphony"
DEFAULT_DTB_PREFIX:imx8qm-var-som = "imx8qm-var-som-symphony"

pkg_postinst:kernel-devicetree:append () {
   rm -f $D/boot/devicetree-*
}

pkg_postinst:kernel-devicetree:append:imx8mq-var-dart () {
    cd $D/boot
    ln -s ${DEFAULT_DTB_PREFIX}-${DEFAULT_DTB}.dtb ${DEFAULT_DTB_PREFIX}.dtb
    ln -s ${DEFAULT_DTB_PREFIX}-legacy-${DEFAULT_DTB}.dtb ${DEFAULT_DTB_PREFIX}-legacy.dtb
}

pkg_postinst:kernel-devicetree:append:imx8qxp-var-som () {
    cd $D/boot
    ln -s ${DEFAULT_DTB_PREFIX}-${DEFAULT_DTB}.dtb ${DEFAULT_DTB_PREFIX}.dtb
}

pkg_postinst:kernel-devicetree:append:imx8qxpb0-var-som () {
    cd $D/boot
    ln -s ${DEFAULT_DTB_PREFIX}-${DEFAULT_DTB}.dtb ${DEFAULT_DTB_PREFIX}.dtb
}

pkg_postinst:kernel-devicetree:append:imx8qm-var-som () {
    cd $D/boot
    ln -s ${DEFAULT_DTB_PREFIX}-${DEFAULT_DTB}.dtb ${DEFAULT_DTB_PREFIX}.dtb
    ln -s imx8qp-var-som-symphony-${DEFAULT_DTB}.dtb imx8qp-var-som-symphony.dtb
    ln -s imx8qm-var-spear-sp8customboard-${DEFAULT_DTB}.dtb imx8qm-var-spear-sp8customboard.dtb
    ln -s imx8qp-var-spear-sp8customboard-${DEFAULT_DTB}.dtb imx8qp-var-spear-sp8customboard.dtb
}

KERNEL_VERSION_SANITY_SKIP="1"
COMPATIBLE_MACHINE = "(mx6-nxp-bsp|mx7-nxp-bsp|mx8-nxp-bsp|mx9-nxp-bsp)"

# Copyright (C) 2012 Khem Raj <raj.khem@gmail.com>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "The kconfig-frontends project aims at centralising \
the effort of keeping an up-to-date, out-of-tree, packaging of the \
kconfig infrastructure, ready for use by third-party projects. \
The kconfig-frontends package provides the kconfig parser, as well as all \
the frontends"
HOMEPAGE = "http://ymorin.is-a-geek.org/projects/kconfig-frontends"
LICENSE = "GPL-2.0"
LIC_FILES_CHKSUM = "file://COPYING;md5=9b8cf60ff39767ff04b671fca8302408"
SECTION = "devel"
DEPENDS += "ncurses flex bison gperf pkgconfig-native"
SRC_URI = "http://ymorin.is-a-geek.org/download/${BPN}/${BP}.tar.xz"

SRC_URI[md5sum] = "b6bd173a39cc32f5e4a580fb1db2e677"
SRC_URI[sha256sum] = "f4bbd7a2727bef3bb0693b37756457c47f2a08e1ed534cb11e4c8640489e6c8e"

S = "${WORKDIR}/${BPN}-${PV}"

inherit autotools
do_configure_prepend () {
	mkdir -p scripts/.autostuff/m4
}

do_install_append() {
	ln -s kconfig-conf ${D}${bindir}/conf
	ln -s kconfig-mconf ${D}${bindir}/mconf
}

EXTRA_OECONF += "--disable-gconf --disable-qconf"

# Some packages have the version preceeding the .so instead properly
# versioned .so.<version>, so we need to reorder and repackage.
SOLIBS = "-${@d.getVar('PV',1)[:-2]}.so"
FILES_SOLIBSDEV = "${libdir}/libkconfig-parser.so"

BBCLASSEXTEND = "native"

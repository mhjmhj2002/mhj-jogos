package com.mhj.jogos.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MhjUtilFile {

	public static List<File> obterArquivos(final String diretorio, final String nomeArquivo) {
		List<MyFile> files = new ArrayList<MyFile>();
		File f = new File(diretorio);
		if (f.isDirectory()) {
			for (File file : f.listFiles()) {
				if (file.getName().startsWith(nomeArquivo)) {
					files.add(new MyFile(file, nomeArquivo));
				}
			}
		}

		return new MyFile().getFiles(files);
	}

	@SuppressWarnings("rawtypes")
	private static class MyFile implements Comparator {

		/**
		 * Constructor.
		 */
		public MyFile() {

		}

		/**
		 * Constructor.
		 * 
		 * @param file
		 *            the file
		 * @param initName
		 *            the InitialName.
		 */
		public MyFile(final File file, final String initName) {
			this.setFile(file, initName);
		}

		/**
		 * the File.
		 */
		private File file;

		/**
		 * the File Date.
		 */
		private Date data;

		/**
		 * @return the file
		 */
		public final File getFile() {
			return file;
		}

		/**
		 * @param file
		 *            the file to set
		 * @param initName
		 *            the initName
		 */
		public final void setFile(final File file, final String initName) {
			this.file = file;

			String name = this.file.getName();

			if (name.equals(initName)) {
				this.setData(new Date());
			} else {
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault());
				try {
					if (name.contains(".")) {
						this.setData(simpleDateFormat
								.parse(name.substring(name.indexOf(initName) + initName.length(), name.indexOf('.'))));
					} else {
						this.setData(simpleDateFormat
								.parse(name.substring(name.indexOf(initName) + initName.length(), name.length())));
					}
				} catch (Exception e) {
//					Logger logger = Logger.getLogger(Utils.class.getName());
//					logger.debug("Erro: " + name + " - " + e.getMessage());
//					logger.debug("Parametros: " + initName);
				}
			}
		}

		/**
		 * @return the data
		 */
		public final Date getData() {
			return data;
		}

		/**
		 * @param data
		 *            the data to set
		 */
		public final void setData(final Date data) {
			this.data = data;
		}

		@Override
		public int compare(final Object o1, final Object o2) {
			int retorno;
			if (((MyFile) o1).getData() == null || ((MyFile) o2).getData() == null) {
				retorno = 0;
			} else {
				retorno = ((MyFile) o1).getData().compareTo(((MyFile) o2).getData());
			}
			return retorno;
		}

		/**
		 * Return the Sorted Files.
		 * 
		 * @param files
		 *            MyFile
		 * @return List<File>
		 */
		@SuppressWarnings("unchecked")
		public List<File> getFiles(final List<MyFile> files) {
			List<File> list;

			if (files == null) {
				list = null;
			} else {
				Collections.sort(files, new MyFile());
				list = new ArrayList<File>();
				for (MyFile myFile : files) {
					list.add(myFile.getFile());
				}
			}
			return list;
		}
	}
}

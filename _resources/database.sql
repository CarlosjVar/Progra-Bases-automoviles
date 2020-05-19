USE [master]
GO
/****** Object:  Database [negocio_autosPartes]    Script Date: 5/19/2020 1:01:50 PM ******/
CREATE DATABASE [negocio_autosPartes]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'negocio_autosPartes', FILENAME = N'D:\Development\SQL\MSSQL15.MSSQLSERVER\MSSQL\DATA\negocio_autosPartes.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'negocio_autosPartes_log', FILENAME = N'D:\Development\SQL\MSSQL15.MSSQLSERVER\MSSQL\DATA\negocio_autosPartes_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT
GO
ALTER DATABASE [negocio_autosPartes] SET COMPATIBILITY_LEVEL = 150
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [negocio_autosPartes].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [negocio_autosPartes] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [negocio_autosPartes] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [negocio_autosPartes] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [negocio_autosPartes] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [negocio_autosPartes] SET ARITHABORT OFF 
GO
ALTER DATABASE [negocio_autosPartes] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [negocio_autosPartes] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [negocio_autosPartes] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [negocio_autosPartes] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [negocio_autosPartes] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [negocio_autosPartes] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [negocio_autosPartes] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [negocio_autosPartes] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [negocio_autosPartes] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [negocio_autosPartes] SET  DISABLE_BROKER 
GO
ALTER DATABASE [negocio_autosPartes] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [negocio_autosPartes] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [negocio_autosPartes] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [negocio_autosPartes] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [negocio_autosPartes] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [negocio_autosPartes] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [negocio_autosPartes] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [negocio_autosPartes] SET RECOVERY FULL 
GO
ALTER DATABASE [negocio_autosPartes] SET  MULTI_USER 
GO
ALTER DATABASE [negocio_autosPartes] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [negocio_autosPartes] SET DB_CHAINING OFF 
GO
ALTER DATABASE [negocio_autosPartes] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [negocio_autosPartes] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [negocio_autosPartes] SET DELAYED_DURABILITY = DISABLED 
GO
EXEC sys.sp_db_vardecimal_storage_format N'negocio_autosPartes', N'ON'
GO
ALTER DATABASE [negocio_autosPartes] SET QUERY_STORE = OFF
GO
USE [negocio_autosPartes]
GO
/****** Object:  Table [dbo].[automoviles]    Script Date: 5/19/2020 1:01:50 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[automoviles](
	[modelo] [varchar](20) NOT NULL,
	[anio] [int] NOT NULL,
	[detalle] [text] NULL,
	[fabricante_id] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[modelo] ASC,
	[anio] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[clientes]    Script Date: 5/19/2020 1:01:50 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[clientes](
	[estado] [varchar](15) NOT NULL,
	[nombre] [varchar](30) NOT NULL,
	[direccion] [varchar](30) NOT NULL,
	[ciudad] [varchar](15) NOT NULL,
	[id] [int] IDENTITY(1,1) NOT NULL,
 CONSTRAINT [PK_ID] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[detalles]    Script Date: 5/19/2020 1:01:50 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[detalles](
	[numero_linea] [int] NOT NULL,
	[consecutivo_orden] [int] NOT NULL,
	[cantidad] [int] NOT NULL,
	[provedor_id] [int] NOT NULL,
	[parte_id] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[consecutivo_orden] ASC,
	[numero_linea] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[es_parte_de]    Script Date: 5/19/2020 1:01:50 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[es_parte_de](
	[parte_id] [int] NOT NULL,
	[auto_modelo] [varchar](20) NOT NULL,
	[auto_anio] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[parte_id] ASC,
	[auto_modelo] ASC,
	[auto_anio] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[estados]    Script Date: 5/19/2020 1:01:50 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[estados](
	[estado] [varchar](15) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[estado] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[fabricantes_autos]    Script Date: 5/19/2020 1:01:50 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[fabricantes_autos](
	[nombre] [varchar](20) NOT NULL,
	[id] [int] IDENTITY(1,1) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],
 CONSTRAINT [AK_NOMBRE_FABRICANTES_AUTOS] UNIQUE NONCLUSTERED 
(
	[nombre] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[fabricantes_partes]    Script Date: 5/19/2020 1:01:50 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[fabricantes_partes](
	[nombre] [varchar](30) NOT NULL,
	[id] [int] IDENTITY(1,1) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],
 CONSTRAINT [AK_NOMBRE_FABRICANTES_PARTES] UNIQUE NONCLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[marcas_partes]    Script Date: 5/19/2020 1:01:50 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[marcas_partes](
	[nombre] [varchar](15) NOT NULL,
	[id] [int] IDENTITY(1,1) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],
 CONSTRAINT [AK_NOMBRE_MARCAS_PARTES] UNIQUE NONCLUSTERED 
(
	[nombre] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ordenes]    Script Date: 5/19/2020 1:01:50 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ordenes](
	[consecutivo] [int] IDENTITY(1,1) NOT NULL,
	[cliente_id] [int] NOT NULL,
	[fecha] [datetime] NOT NULL,
	[monto_total] [decimal](17, 2) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[consecutivo] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[organizaciones]    Script Date: 5/19/2020 1:01:50 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[organizaciones](
	[surrogate_key] [int] NOT NULL,
	[encargado_nombre] [varchar](20) NOT NULL,
	[encargado_telefono] [varchar](9) NOT NULL,
	[encargado_cargo] [varchar](10) NOT NULL,
	[cedula_juridica] [decimal](10, 0) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[cedula_juridica] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[partes]    Script Date: 5/19/2020 1:01:50 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[partes](
	[nombre] [varchar](30) NOT NULL,
	[id] [int] IDENTITY(1,1) NOT NULL,
	[marca_id] [int] NOT NULL,
	[fabricante_id] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],
 CONSTRAINT [AK_NOMBRE] UNIQUE NONCLUSTERED 
(
	[nombre] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[personas]    Script Date: 5/19/2020 1:01:50 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[personas](
	[cedula] [int] NOT NULL,
	[id_cliente] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[cedula] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[provedores]    Script Date: 5/19/2020 1:01:50 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[provedores](
	[ciudad] [varchar](15) NOT NULL,
	[direccion] [varchar](30) NOT NULL,
	[nombre_contacto] [varchar](20) NOT NULL,
	[id] [int] IDENTITY(1,1) NOT NULL,
	[telefono] [varchar](9) NOT NULL,
	[nombre] [varchar](30) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[proveido_por]    Script Date: 5/19/2020 1:01:50 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[proveido_por](
	[parte_id] [int] NOT NULL,
	[provedor_id] [int] NOT NULL,
	[precio] [decimal](9, 2) NOT NULL,
	[porcentaje_ganancia] [decimal](5, 2) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[parte_id] ASC,
	[provedor_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[telefonos_persona]    Script Date: 5/19/2020 1:01:50 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[telefonos_persona](
	[cedula] [int] NOT NULL,
	[telefono] [varchar](9) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[cedula] ASC,
	[telefono] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[ordenes] ADD  DEFAULT (getdate()) FOR [fecha]
GO
ALTER TABLE [dbo].[automoviles]  WITH CHECK ADD FOREIGN KEY([fabricante_id])
REFERENCES [dbo].[fabricantes_autos] ([id])
GO
ALTER TABLE [dbo].[clientes]  WITH CHECK ADD FOREIGN KEY([estado])
REFERENCES [dbo].[estados] ([estado])
GO
ALTER TABLE [dbo].[detalles]  WITH CHECK ADD FOREIGN KEY([consecutivo_orden])
REFERENCES [dbo].[ordenes] ([consecutivo])
GO
ALTER TABLE [dbo].[detalles]  WITH CHECK ADD FOREIGN KEY([parte_id])
REFERENCES [dbo].[partes] ([id])
GO
ALTER TABLE [dbo].[detalles]  WITH CHECK ADD FOREIGN KEY([provedor_id])
REFERENCES [dbo].[provedores] ([id])
GO
ALTER TABLE [dbo].[es_parte_de]  WITH CHECK ADD FOREIGN KEY([parte_id])
REFERENCES [dbo].[partes] ([id])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[es_parte_de]  WITH CHECK ADD FOREIGN KEY([auto_modelo], [auto_anio])
REFERENCES [dbo].[automoviles] ([modelo], [anio])
GO
ALTER TABLE [dbo].[ordenes]  WITH CHECK ADD FOREIGN KEY([cliente_id])
REFERENCES [dbo].[clientes] ([id])
GO
ALTER TABLE [dbo].[organizaciones]  WITH CHECK ADD FOREIGN KEY([surrogate_key])
REFERENCES [dbo].[clientes] ([id])
GO
ALTER TABLE [dbo].[partes]  WITH CHECK ADD FOREIGN KEY([fabricante_id])
REFERENCES [dbo].[fabricantes_partes] ([id])
GO
ALTER TABLE [dbo].[partes]  WITH CHECK ADD FOREIGN KEY([marca_id])
REFERENCES [dbo].[marcas_partes] ([id])
GO
ALTER TABLE [dbo].[personas]  WITH CHECK ADD FOREIGN KEY([id_cliente])
REFERENCES [dbo].[clientes] ([id])
GO
ALTER TABLE [dbo].[proveido_por]  WITH CHECK ADD FOREIGN KEY([parte_id])
REFERENCES [dbo].[partes] ([id])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[proveido_por]  WITH CHECK ADD FOREIGN KEY([provedor_id])
REFERENCES [dbo].[provedores] ([id])
GO
ALTER TABLE [dbo].[telefonos_persona]  WITH CHECK ADD FOREIGN KEY([cedula])
REFERENCES [dbo].[personas] ([cedula])
GO
USE [master]
GO
ALTER DATABASE [negocio_autosPartes] SET  READ_WRITE 
GO

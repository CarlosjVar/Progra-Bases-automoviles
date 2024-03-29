USE [negocio_autosPartes]
GO
/****** Object:  Table [dbo].[automoviles]    Script Date: 1/6/2020 18:12:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[automoviles](
	[modelo] [varchar](20) NOT NULL,
	[anio] [int] NOT NULL,
	[detalle] [text] NULL,
	[fabricante_id] [int] NOT NULL,
 CONSTRAINT [PK_AUTOMOVILES] PRIMARY KEY CLUSTERED 
(
	[modelo] ASC,
	[anio] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[clientes]    Script Date: 1/6/2020 18:12:26 ******/
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
 CONSTRAINT [PK_CLIENTES] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[detalles]    Script Date: 1/6/2020 18:12:26 ******/
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
 CONSTRAINT [PK_DETALLES] PRIMARY KEY CLUSTERED 
(
	[consecutivo_orden] ASC,
	[numero_linea] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[es_parte_de]    Script Date: 1/6/2020 18:12:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[es_parte_de](
	[parte_id] [int] NOT NULL,
	[auto_modelo] [varchar](20) NOT NULL,
	[auto_anio] [int] NOT NULL,
 CONSTRAINT [PK_ES_PARTE_DE] PRIMARY KEY CLUSTERED 
(
	[parte_id] ASC,
	[auto_modelo] ASC,
	[auto_anio] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[estados]    Script Date: 1/6/2020 18:12:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[estados](
	[estado] [varchar](15) NOT NULL,
 CONSTRAINT [PK_ESTADOS] PRIMARY KEY CLUSTERED 
(
	[estado] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[fabricantes_autos]    Script Date: 1/6/2020 18:12:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[fabricantes_autos](
	[nombre] [varchar](20) NOT NULL,
	[id] [int] IDENTITY(1,1) NOT NULL,
 CONSTRAINT [PK_FABRICANTES_AUTOMOVILES] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[fabricantes_partes]    Script Date: 1/6/2020 18:12:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[fabricantes_partes](
	[nombre] [varchar](30) NOT NULL,
	[id] [int] IDENTITY(1,1) NOT NULL,
 CONSTRAINT [PK_FABRICANTES_PARTES] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[marcas_partes]    Script Date: 1/6/2020 18:12:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[marcas_partes](
	[nombre] [varchar](15) NOT NULL,
	[id] [int] IDENTITY(1,1) NOT NULL,
 CONSTRAINT [PK_MARCAS_PARTES] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ordenes]    Script Date: 1/6/2020 18:12:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ordenes](
	[consecutivo] [int] IDENTITY(1,1) NOT NULL,
	[cliente_id] [int] NOT NULL,
	[monto_total] [decimal](17, 2) NOT NULL,
	[fecha] [date] NOT NULL,
 CONSTRAINT [PK_ORDENES] PRIMARY KEY CLUSTERED 
(
	[consecutivo] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[organizaciones]    Script Date: 1/6/2020 18:12:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[organizaciones](
	[encargado_nombre] [varchar](20) NOT NULL,
	[encargado_telefono] [varchar](9) NOT NULL,
	[encargado_cargo] [varchar](10) NOT NULL,
	[cedula_juridica] [decimal](10, 0) NOT NULL,
	[id_cliente] [int] NOT NULL,
 CONSTRAINT [PK_ORGANIZACIONES] PRIMARY KEY CLUSTERED 
(
	[cedula_juridica] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[partes]    Script Date: 1/6/2020 18:12:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[partes](
	[nombre] [varchar](30) NOT NULL,
	[id] [int] IDENTITY(1,1) NOT NULL,
	[marca_id] [int] NOT NULL,
	[fabricante_id] [int] NOT NULL,
 CONSTRAINT [PK_PARTES] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[personas]    Script Date: 1/6/2020 18:12:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[personas](
	[cedula] [int] NOT NULL,
	[id_cliente] [int] NOT NULL,
 CONSTRAINT [PK_PERSONAS] PRIMARY KEY CLUSTERED 
(
	[cedula] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[provedores]    Script Date: 1/6/2020 18:12:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[provedores](
	[ciudad] [varchar](15) NOT NULL,
	[direccion] [varchar](30) NOT NULL,
	[id] [int] IDENTITY(1,1) NOT NULL,
	[telefono] [varchar](9) NOT NULL,
	[nombre] [varchar](30) NOT NULL,
	[nombre_contacto] [varchar](50) NOT NULL,
 CONSTRAINT [PK_PROVEDORES] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[proveido_por]    Script Date: 1/6/2020 18:12:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[proveido_por](
	[parte_id] [int] NOT NULL,
	[provedor_id] [int] NOT NULL,
	[precio] [decimal](9, 2) NOT NULL,
	[porcentaje_ganancia] [decimal](5, 2) NOT NULL,
 CONSTRAINT [PK_PROVEIDO_POR] PRIMARY KEY CLUSTERED 
(
	[parte_id] ASC,
	[provedor_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[telefonos_persona]    Script Date: 1/6/2020 18:12:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[telefonos_persona](
	[cedula] [int] NOT NULL,
	[telefono] [varchar](9) NOT NULL,
 CONSTRAINT [PK_TELEFONOS] PRIMARY KEY CLUSTERED 
(
	[cedula] ASC,
	[telefono] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[automoviles] ([modelo], [anio], [detalle], [fabricante_id]) VALUES (N'Camry', 2015, N'Mid-size sedan', 1)
INSERT [dbo].[automoviles] ([modelo], [anio], [detalle], [fabricante_id]) VALUES (N'Corolla', 2010, N'Compact', 1)
INSERT [dbo].[automoviles] ([modelo], [anio], [detalle], [fabricante_id]) VALUES (N'CX-5', 2012, N'Compact crossover SUV', 4)
INSERT [dbo].[automoviles] ([modelo], [anio], [detalle], [fabricante_id]) VALUES (N'Eclipse', 2010, N'Sport coupe', 6)
INSERT [dbo].[automoviles] ([modelo], [anio], [detalle], [fabricante_id]) VALUES (N'F18', 2011, N'5 Series long wheelbase', 3)
INSERT [dbo].[automoviles] ([modelo], [anio], [detalle], [fabricante_id]) VALUES (N'Fox', 2013, N'Hatchback', 5)
INSERT [dbo].[automoviles] ([modelo], [anio], [detalle], [fabricante_id]) VALUES (N'Golf', 2011, N'Compact', 5)
INSERT [dbo].[automoviles] ([modelo], [anio], [detalle], [fabricante_id]) VALUES (N'Lancer', 2013, N'Small sedan', 6)
INSERT [dbo].[automoviles] ([modelo], [anio], [detalle], [fabricante_id]) VALUES (N'Mazda 6', 2014, N'Family sedan', 4)
INSERT [dbo].[automoviles] ([modelo], [anio], [detalle], [fabricante_id]) VALUES (N'Murano', 2015, N'Mid-size crossover', 2)
INSERT [dbo].[automoviles] ([modelo], [anio], [detalle], [fabricante_id]) VALUES (N'Pajero', 2008, N'Sophisticated full-time dual-range 4WD', 6)
INSERT [dbo].[automoviles] ([modelo], [anio], [detalle], [fabricante_id]) VALUES (N'Prius', 2011, N'Full hybrid electric mid-size hatchback', 1)
INSERT [dbo].[automoviles] ([modelo], [anio], [detalle], [fabricante_id]) VALUES (N'Sentra', 2007, N'Compact', 2)
INSERT [dbo].[automoviles] ([modelo], [anio], [detalle], [fabricante_id]) VALUES (N'Terrano', 2012, N'Crossover', 2)
INSERT [dbo].[automoviles] ([modelo], [anio], [detalle], [fabricante_id]) VALUES (N'X-Trail', 2015, N'Mid-size crossover', 2)
INSERT [dbo].[automoviles] ([modelo], [anio], [detalle], [fabricante_id]) VALUES (N'X6 M', 2015, N'Sports Activity Coupe', 3)
INSERT [dbo].[automoviles] ([modelo], [anio], [detalle], [fabricante_id]) VALUES (N'Yaris', 2008, N'Subcompact', 1)
SET IDENTITY_INSERT [dbo].[clientes] ON 

INSERT [dbo].[clientes] ([estado], [nombre], [direccion], [ciudad], [id]) VALUES (N'ACTIVO', N'Andres Aguilar', N'Turrialba, Cartago', N'Cartago', 9)
INSERT [dbo].[clientes] ([estado], [nombre], [direccion], [ciudad], [id]) VALUES (N'ACTIVO', N'ACME Solutions', N'San Jose centro', N'San Jose', 10)
SET IDENTITY_INSERT [dbo].[clientes] OFF
INSERT [dbo].[es_parte_de] ([parte_id], [auto_modelo], [auto_anio]) VALUES (1, N'Lancer', 2013)
INSERT [dbo].[es_parte_de] ([parte_id], [auto_modelo], [auto_anio]) VALUES (1, N'Mazda 6', 2014)
INSERT [dbo].[es_parte_de] ([parte_id], [auto_modelo], [auto_anio]) VALUES (1, N'X-Trail', 2015)
INSERT [dbo].[es_parte_de] ([parte_id], [auto_modelo], [auto_anio]) VALUES (2, N'X-Trail', 2015)
INSERT [dbo].[es_parte_de] ([parte_id], [auto_modelo], [auto_anio]) VALUES (2, N'Yaris', 2008)
INSERT [dbo].[es_parte_de] ([parte_id], [auto_modelo], [auto_anio]) VALUES (3, N'Yaris', 2008)
INSERT [dbo].[es_parte_de] ([parte_id], [auto_modelo], [auto_anio]) VALUES (4, N'Lancer', 2013)
INSERT [dbo].[es_parte_de] ([parte_id], [auto_modelo], [auto_anio]) VALUES (6, N'Lancer', 2013)
INSERT [dbo].[estados] ([estado]) VALUES (N'ACTIVO')
INSERT [dbo].[estados] ([estado]) VALUES (N'INACTIVO')
INSERT [dbo].[estados] ([estado]) VALUES (N'SUSPENDIDO')
SET IDENTITY_INSERT [dbo].[fabricantes_autos] ON 

INSERT [dbo].[fabricantes_autos] ([nombre], [id]) VALUES (N'BMW', 3)
INSERT [dbo].[fabricantes_autos] ([nombre], [id]) VALUES (N'Mazda', 4)
INSERT [dbo].[fabricantes_autos] ([nombre], [id]) VALUES (N'Mitsubishi', 6)
INSERT [dbo].[fabricantes_autos] ([nombre], [id]) VALUES (N'Nissan', 2)
INSERT [dbo].[fabricantes_autos] ([nombre], [id]) VALUES (N'Toyota', 1)
INSERT [dbo].[fabricantes_autos] ([nombre], [id]) VALUES (N'Volkswagen', 5)
SET IDENTITY_INSERT [dbo].[fabricantes_autos] OFF
SET IDENTITY_INSERT [dbo].[fabricantes_partes] ON 

INSERT [dbo].[fabricantes_partes] ([nombre], [id]) VALUES (N'International Spare Parts', 1)
INSERT [dbo].[fabricantes_partes] ([nombre], [id]) VALUES (N'Auto Spare Parts', 2)
INSERT [dbo].[fabricantes_partes] ([nombre], [id]) VALUES (N'AJS Auto Parts', 3)
INSERT [dbo].[fabricantes_partes] ([nombre], [id]) VALUES (N'Discounted Parts', 4)
SET IDENTITY_INSERT [dbo].[fabricantes_partes] OFF
SET IDENTITY_INSERT [dbo].[marcas_partes] ON 

INSERT [dbo].[marcas_partes] ([nombre], [id]) VALUES (N'Armstrong', 9)
INSERT [dbo].[marcas_partes] ([nombre], [id]) VALUES (N'Bondo', 4)
INSERT [dbo].[marcas_partes] ([nombre], [id]) VALUES (N'Delphi', 1)
INSERT [dbo].[marcas_partes] ([nombre], [id]) VALUES (N'Falken', 2)
INSERT [dbo].[marcas_partes] ([nombre], [id]) VALUES (N'Fuller', 8)
INSERT [dbo].[marcas_partes] ([nombre], [id]) VALUES (N'Goyo', 5)
INSERT [dbo].[marcas_partes] ([nombre], [id]) VALUES (N'Kelly', 3)
INSERT [dbo].[marcas_partes] ([nombre], [id]) VALUES (N'Kleber', 7)
INSERT [dbo].[marcas_partes] ([nombre], [id]) VALUES (N'Koni', 6)
INSERT [dbo].[marcas_partes] ([nombre], [id]) VALUES (N'Walker', 10)
SET IDENTITY_INSERT [dbo].[marcas_partes] OFF
INSERT [dbo].[organizaciones] ([encargado_nombre], [encargado_telefono], [encargado_cargo], [cedula_juridica], [id_cliente]) VALUES (N'John Doe', N'8888-4444', N'Gerente', CAST(1234567891 AS Decimal(10, 0)), 10)
SET IDENTITY_INSERT [dbo].[partes] ON 

INSERT [dbo].[partes] ([nombre], [id], [marca_id], [fabricante_id]) VALUES (N'PARTE_1', 1, 2, 3)
INSERT [dbo].[partes] ([nombre], [id], [marca_id], [fabricante_id]) VALUES (N'PARTE_2', 2, 3, 4)
INSERT [dbo].[partes] ([nombre], [id], [marca_id], [fabricante_id]) VALUES (N'PARTE_3', 3, 7, 1)
INSERT [dbo].[partes] ([nombre], [id], [marca_id], [fabricante_id]) VALUES (N'PARTE_4', 4, 6, 2)
INSERT [dbo].[partes] ([nombre], [id], [marca_id], [fabricante_id]) VALUES (N'PARTE_5', 5, 5, 1)
INSERT [dbo].[partes] ([nombre], [id], [marca_id], [fabricante_id]) VALUES (N'PARTE_6', 6, 4, 4)
SET IDENTITY_INSERT [dbo].[partes] OFF
INSERT [dbo].[personas] ([cedula], [id_cliente]) VALUES (305320241, 9)
SET IDENTITY_INSERT [dbo].[provedores] ON 

INSERT [dbo].[provedores] ([ciudad], [direccion], [id], [telefono], [nombre], [nombre_contacto]) VALUES (N'San José', N'Pavas', 1, N'6503-3078', N'Auto Repuestos Jiménez', N'Sergio Mancilla Alfonso')
INSERT [dbo].[provedores] ([ciudad], [direccion], [id], [telefono], [nombre], [nombre_contacto]) VALUES (N'Naranjo', N'700 m N peaje', 2, N'6184-0940', N'Repuestos Zúñiga', N'Ruben Mir Mestres')
INSERT [dbo].[provedores] ([ciudad], [direccion], [id], [telefono], [nombre], [nombre_contacto]) VALUES (N'Alajuela', N'Plaza Ferias', 3, N'6121-9649', N'J y A Autopartes', N'Marina Paez Melgarejo')
INSERT [dbo].[provedores] ([ciudad], [direccion], [id], [telefono], [nombre], [nombre_contacto]) VALUES (N'Grecia', N'200m S Palí', 4, N'8467-6801', N'Servicios Omega', N'Felipe Escudero Andrades')
INSERT [dbo].[provedores] ([ciudad], [direccion], [id], [telefono], [nombre], [nombre_contacto]) VALUES (N'Puntarenas', N'250 O Mercado Municipal', 5, N'8840-4529', N'Repuestos ABZ', N'Marcos Vara Rua')
INSERT [dbo].[provedores] ([ciudad], [direccion], [id], [telefono], [nombre], [nombre_contacto]) VALUES (N'Limón', N'300 sur parque', 6, N'7860-7436', N'Autopartes USA', N'Diego Teran Quintela')
INSERT [dbo].[provedores] ([ciudad], [direccion], [id], [telefono], [nombre], [nombre_contacto]) VALUES (N'Liberia', N'150 E Plaza Santa Rosa', 7, N'7450-2614', N'Super Repuestos', N'Irene Carranza Pellicer')
INSERT [dbo].[provedores] ([ciudad], [direccion], [id], [telefono], [nombre], [nombre_contacto]) VALUES (N'Heredia', N'Parque Los Ángeles', 8, N'7403-9438', N'Megapartes Martínez', N'Beatriz Lucena Duque')
INSERT [dbo].[provedores] ([ciudad], [direccion], [id], [telefono], [nombre], [nombre_contacto]) VALUES (N'Cartago', N'350m E Basílica', 9, N'7212-6866', N'LACOR Repuestos', N'Eva Palacios Alejo')
INSERT [dbo].[provedores] ([ciudad], [direccion], [id], [telefono], [nombre], [nombre_contacto]) VALUES (N'Guadalupe', N'150m E Mall El Dorado', 10, N'6949-5933', N'Autopartes Mandi', N'Francisco Jose Salguero Bruno')
SET IDENTITY_INSERT [dbo].[provedores] OFF
INSERT [dbo].[proveido_por] ([parte_id], [provedor_id], [precio], [porcentaje_ganancia]) VALUES (1, 2, CAST(5000.00 AS Decimal(9, 2)), CAST(30.00 AS Decimal(5, 2)))
INSERT [dbo].[proveido_por] ([parte_id], [provedor_id], [precio], [porcentaje_ganancia]) VALUES (1, 6, CAST(3000.00 AS Decimal(9, 2)), CAST(20.00 AS Decimal(5, 2)))
SET ANSI_PADDING ON
GO
/****** Object:  Index [AK_FABRICANTES_AUTOMOVILES_NOMBRE]    Script Date: 1/6/2020 18:12:26 ******/
ALTER TABLE [dbo].[fabricantes_autos] ADD  CONSTRAINT [AK_FABRICANTES_AUTOMOVILES_NOMBRE] UNIQUE NONCLUSTERED 
(
	[nombre] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
/****** Object:  Index [AK_FABRICANTES_PARTES_NOMBRE]    Script Date: 1/6/2020 18:12:26 ******/
ALTER TABLE [dbo].[fabricantes_partes] ADD  CONSTRAINT [AK_FABRICANTES_PARTES_NOMBRE] UNIQUE NONCLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [AK_MARCAS_PARTES_NOMBRE]    Script Date: 1/6/2020 18:12:26 ******/
ALTER TABLE [dbo].[marcas_partes] ADD  CONSTRAINT [AK_MARCAS_PARTES_NOMBRE] UNIQUE NONCLUSTERED 
(
	[nombre] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
/****** Object:  Index [AK_ORGANIZACIONES_ID_CLIENTE]    Script Date: 1/6/2020 18:12:26 ******/
ALTER TABLE [dbo].[organizaciones] ADD  CONSTRAINT [AK_ORGANIZACIONES_ID_CLIENTE] UNIQUE NONCLUSTERED 
(
	[id_cliente] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [AK_PARTES_NOMBRE]    Script Date: 1/6/2020 18:12:26 ******/
ALTER TABLE [dbo].[partes] ADD  CONSTRAINT [AK_PARTES_NOMBRE] UNIQUE NONCLUSTERED 
(
	[nombre] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
/****** Object:  Index [AK_PERSONAS_ID_CLIENTE]    Script Date: 1/6/2020 18:12:26 ******/
ALTER TABLE [dbo].[personas] ADD  CONSTRAINT [AK_PERSONAS_ID_CLIENTE] UNIQUE NONCLUSTERED 
(
	[id_cliente] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [AK_PROVEEDORES_NOMBRE]    Script Date: 1/6/2020 18:12:26 ******/
ALTER TABLE [dbo].[provedores] ADD  CONSTRAINT [AK_PROVEEDORES_NOMBRE] UNIQUE NONCLUSTERED 
(
	[nombre] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
ALTER TABLE [dbo].[ordenes] ADD  DEFAULT (getdate()) FOR [fecha]
GO
ALTER TABLE [dbo].[automoviles]  WITH NOCHECK ADD  CONSTRAINT [FK_AUTOMOVILES_FABRICANTES] FOREIGN KEY([fabricante_id])
REFERENCES [dbo].[fabricantes_autos] ([id])
GO
ALTER TABLE [dbo].[automoviles] CHECK CONSTRAINT [FK_AUTOMOVILES_FABRICANTES]
GO
ALTER TABLE [dbo].[clientes]  WITH CHECK ADD  CONSTRAINT [FK_CLIENTES_ESTADOS] FOREIGN KEY([estado])
REFERENCES [dbo].[estados] ([estado])
GO
ALTER TABLE [dbo].[clientes] CHECK CONSTRAINT [FK_CLIENTES_ESTADOS]
GO
ALTER TABLE [dbo].[detalles]  WITH CHECK ADD  CONSTRAINT [FK_DETALLES_ORDENES] FOREIGN KEY([consecutivo_orden])
REFERENCES [dbo].[ordenes] ([consecutivo])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[detalles] CHECK CONSTRAINT [FK_DETALLES_ORDENES]
GO
ALTER TABLE [dbo].[detalles]  WITH CHECK ADD  CONSTRAINT [FK_DETALLES_PARTES] FOREIGN KEY([parte_id])
REFERENCES [dbo].[partes] ([id])
GO
ALTER TABLE [dbo].[detalles] CHECK CONSTRAINT [FK_DETALLES_PARTES]
GO
ALTER TABLE [dbo].[detalles]  WITH CHECK ADD  CONSTRAINT [FK_DETALLES_PROVEDORES] FOREIGN KEY([provedor_id])
REFERENCES [dbo].[provedores] ([id])
GO
ALTER TABLE [dbo].[detalles] CHECK CONSTRAINT [FK_DETALLES_PROVEDORES]
GO
ALTER TABLE [dbo].[es_parte_de]  WITH CHECK ADD  CONSTRAINT [FK_ES_PARTE_DE_AUTOMOVILES] FOREIGN KEY([auto_modelo], [auto_anio])
REFERENCES [dbo].[automoviles] ([modelo], [anio])
GO
ALTER TABLE [dbo].[es_parte_de] CHECK CONSTRAINT [FK_ES_PARTE_DE_AUTOMOVILES]
GO
ALTER TABLE [dbo].[es_parte_de]  WITH CHECK ADD  CONSTRAINT [FK_ES_PARTE_DE_PARTES] FOREIGN KEY([parte_id])
REFERENCES [dbo].[partes] ([id])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[es_parte_de] CHECK CONSTRAINT [FK_ES_PARTE_DE_PARTES]
GO
ALTER TABLE [dbo].[ordenes]  WITH CHECK ADD  CONSTRAINT [FK_ORDENES_CLIENTES] FOREIGN KEY([cliente_id])
REFERENCES [dbo].[clientes] ([id])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[ordenes] CHECK CONSTRAINT [FK_ORDENES_CLIENTES]
GO
ALTER TABLE [dbo].[organizaciones]  WITH CHECK ADD  CONSTRAINT [FK_ORGANIZACIONES_CLIENTES] FOREIGN KEY([id_cliente])
REFERENCES [dbo].[clientes] ([id])
GO
ALTER TABLE [dbo].[organizaciones] CHECK CONSTRAINT [FK_ORGANIZACIONES_CLIENTES]
GO
ALTER TABLE [dbo].[partes]  WITH CHECK ADD  CONSTRAINT [FK_PARTES_FABRICANTES] FOREIGN KEY([fabricante_id])
REFERENCES [dbo].[fabricantes_partes] ([id])
GO
ALTER TABLE [dbo].[partes] CHECK CONSTRAINT [FK_PARTES_FABRICANTES]
GO
ALTER TABLE [dbo].[partes]  WITH CHECK ADD  CONSTRAINT [FK_PARTES_MARCAS] FOREIGN KEY([marca_id])
REFERENCES [dbo].[marcas_partes] ([id])
GO
ALTER TABLE [dbo].[partes] CHECK CONSTRAINT [FK_PARTES_MARCAS]
GO
ALTER TABLE [dbo].[personas]  WITH CHECK ADD  CONSTRAINT [FK_PERSONAS_CLIENTES] FOREIGN KEY([id_cliente])
REFERENCES [dbo].[clientes] ([id])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[personas] CHECK CONSTRAINT [FK_PERSONAS_CLIENTES]
GO
ALTER TABLE [dbo].[proveido_por]  WITH CHECK ADD  CONSTRAINT [FK_PROVEIDO_POR_PARTES] FOREIGN KEY([parte_id])
REFERENCES [dbo].[partes] ([id])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[proveido_por] CHECK CONSTRAINT [FK_PROVEIDO_POR_PARTES]
GO
ALTER TABLE [dbo].[proveido_por]  WITH CHECK ADD  CONSTRAINT [FK_PROVEIDO_POR_PROVEDORES] FOREIGN KEY([provedor_id])
REFERENCES [dbo].[provedores] ([id])
GO
ALTER TABLE [dbo].[proveido_por] CHECK CONSTRAINT [FK_PROVEIDO_POR_PROVEDORES]
GO
ALTER TABLE [dbo].[telefonos_persona]  WITH CHECK ADD  CONSTRAINT [FK_TELEFONOS_PERSONAS] FOREIGN KEY([cedula])
REFERENCES [dbo].[personas] ([cedula])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[telefonos_persona] CHECK CONSTRAINT [FK_TELEFONOS_PERSONAS]
GO
ALTER TABLE [dbo].[organizaciones]  WITH CHECK ADD  CONSTRAINT [CHECK_ORGANIZACIONES_CEDULA] CHECK  (([cedula_juridica]>(999999999) AND [cedula_juridica]<(10000000000.)))
GO
ALTER TABLE [dbo].[organizaciones] CHECK CONSTRAINT [CHECK_ORGANIZACIONES_CEDULA]
GO
ALTER TABLE [dbo].[personas]  WITH CHECK ADD  CONSTRAINT [CHECK_PERSONA_CEDULA] CHECK  (([personas].[cedula]>(99999999) AND [personas].[cedula]<(1000000000)))
GO
ALTER TABLE [dbo].[personas] CHECK CONSTRAINT [CHECK_PERSONA_CEDULA]
GO

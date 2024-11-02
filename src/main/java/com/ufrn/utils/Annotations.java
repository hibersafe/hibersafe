package com.ufrn.utils;

public class Annotations {
    public static final String HIBERNATE_ANNOTATIONS = "@Any" + "|@AnyDiscriminator" + "|@AnyDiscriminatorValue" + "|@AnyDiscriminatorValues" 
    		+ "|@AnyKeyJavaClass" + "|@AnyKeyJavaType" + "|@AnyKeyJdbcType" + "|@AnyKeyJdbcTypeCode" + "|@Array" + 
    		"|@AttributeAccessor" + "|@AttributeBinderType" + "|@Bag" + "|@BatchSize" + "|@Cache" + "|@CacheConcurrencyStrategy" + 
    		"|@CacheLayout" + "|@CacheModeType" + "|@Cascade" + "|@CascadeType" + "|@Check" + "|@Checks" + "|@Collate" + 
    		"|@CollectionId" + "|@CollectionIdJavaType" + "|@CollectionIdJdbcType" + "|@CollectionIdJdbcTypeCode" + 
    		"|@CollectionIdMutability" + "|@CollectionIdType" + "|@CollectionType" + "|@CollectionTypeRegistration" + 
    		"|@CollectionTypeRegistrations" + "|@ColumnDefault" + "|@ColumnTransformer" + "|@ColumnTransformers" + 
    		"|@Columns" + "|@Comment" + "|@Comments" + "|@CompositeType" + "|@CompositeTypeRegistration" + 
    		"|@CompositeTypeRegistrations" + "|@ConcreteProxy" + "|@ConverterRegistration" + "|@ConverterRegistrations" + 
    		"|@CreationTimestamp" + "|@CurrentTimestamp" + "|@DialectOverride.Check" + "|@DialectOverride.Checks" + 
    		"|@DialectOverride.ColumnDefault" + "|@DialectOverride.ColumnDefaults" + "|@DialectOverride.DiscriminatorFormula" + 
    		"|@DialectOverride.DiscriminatorFormulas" + "|@DialectOverride.FilterDefOverrides" + "|@DialectOverride.FilterDefs" + 
    		"|@DialectOverride.FilterOverrides" + "|@DialectOverride.Filters" + "|@DialectOverride.Formula" + 
    		"|@DialectOverride.Formulas" + "|@DialectOverride.GeneratedColumn" + "|@DialectOverride.GeneratedColumns" + 
    		"|@DialectOverride.JoinFormula" + "|@DialectOverride.JoinFormulas" + "|@DialectOverride.OrderBy" + 
    		"|@DialectOverride.OrderBys" + "|@DialectOverride.OverridesAnnotation" + "|@DialectOverride.SQLDelete" + 
    		"|@DialectOverride.SQLDeleteAll" + "|@DialectOverride.SQLDeleteAlls" + "|@DialectOverride.SQLDeletes" + 
    		"|@DialectOverride.SQLInsert" + "|@DialectOverride.SQLInserts" + "|@DialectOverride.SQLOrder" + 
    		"|@DialectOverride.SQLOrders" + "|@DialectOverride.SQLRestriction" + "|@DialectOverride.SQLRestrictions" + 
    		"|@DialectOverride.SQLSelect" + "|@DialectOverride.SQLSelects" + "|@DialectOverride.SQLUpdate" + 
    		"|@DialectOverride.SQLUpdates" + "|@DialectOverride.Version" + "|@DialectOverride.Where" + 
    		"|@DialectOverride.Wheres" + "|@DialectOverride" + "|@DiscriminatorFormula" + "|@DiscriminatorOptions" + 
    		"|@DynamicInsert" + "|@DynamicUpdate" + "|@EmbeddableInstantiator" + "|@EmbeddableInstantiatorRegistration" + 
    		"|@EmbeddableInstantiatorRegistrations" + "|@Fetch" + "|@FetchMode" + "|@FetchProfile.FetchOverride" + 
    		"|@FetchProfile" + "|@FetchProfileOverride" + "|@FetchProfileOverrides" + "|@FetchProfiles" + 
    		"|@Filter" + "|@FilterDef" + "|@FilterDefs" + "|@FilterJoinTable" + "|@FilterJoinTables" + 
    		"|@Filters" + "|@FlushModeType" + "|@ForeignKey" + "|@Formula" + "|@FractionalSeconds" + 
    		"|@Generated" + "|@GeneratedColumn" + "|@GenerationTime" + "|@GeneratorType" + "|@GenericGenerator" + 
    		"|@GenericGenerators" + "|@HQLSelect" + "|@IdGeneratorType" + "|@Immutable" + "|@Imported" + "|@Index" + 
    		"|@IndexColumn" + "|@Instantiator" + "|@JavaType" + "|@JavaTypeRegistration" + "|@JavaTypeRegistrations" + 
    		"|@JdbcType" + "|@JdbcTypeCode" + "|@JdbcTypeRegistration" + "|@JdbcTypeRegistrations" + "|@JoinColumnOrFormula" + 
    		"|@JoinColumnsOrFormulas" + "|@JoinFormula" + "|@LazyCollection" + "|@LazyCollectionOption" + "|@LazyGroup" + 
    		"|@LazyToOne" + "|@LazyToOneOption" + "|@ListIndexBase" + "|@ListIndexJavaType" + "|@ListIndexJdbcType" + 
    		"|@ListIndexJdbcTypeCode" + "|@Loader" + "|@ManyToAny" + "|@MapKeyCompositeType" + "|@MapKeyJavaType" + 
    		"|@MapKeyJdbcType" + "|@MapKeyJdbcTypeCode" + "|@MapKeyMutability" + "|@MapKeyType" + "|@Mutability" + 
    		"|@NamedNativeQueries" + "|@NamedNativeQuery" + "|@NamedQueries" + "|@NamedQuery" + "|@Nationalized" + 
    		"|@NaturalId" + "|@NaturalIdCache" + "|@NoAttributeConverter" + "|@NotFound" + "|@NotFoundAction" + 
    		"|@OnDelete" + "|@OnDeleteAction" + "|@OptimisticLock" + "|@OptimisticLockType" + "|@OptimisticLocking" + 
    		"|@OrderBy" + "|@ParamDef" + "|@Parameter" + "|@Parent" + "|@PartitionKey" + "|@Persister" + "|@Polymorphism" + 
    		"|@PolymorphismType" + "|@Proxy" + "|@QueryCacheLayout" + "|@QueryHints" + "|@ResultCheckStyle" + "|@RowId" + 
    		"|@SQLDelete" + "|@SQLDeleteAll" + "|@SQLDeletes" + "|@SQLInsert" + "|@SQLInserts" + "|@SQLJoinTableRestriction" + 
    		"|@SQLOrder" + "|@SQLRestriction" + "|@SQLSelect" + "|@SQLUpdate" + "|@SQLUpdates" + "|@SecondaryRow" + 
    		"|@SecondaryRows" + "|@SelectBeforeUpdate" + "|@SoftDelete.UnspecifiedConversion" + "|@SoftDelete" + 
    		"|@SoftDeleteType" + "|@SortComparator" + "|@SortNatural" + "|@Source" + "|@SourceType" + "|@SqlFragmentAlias" + 
    		"|@Struct" + "|@Subselect" + "|@Synchronize" + "|@Table" + "|@Tables" + "|@Target" + "|@TenantId" + 
    		"|@TimeZoneColumn" + "|@TimeZoneStorage" + "|@TimeZoneStorageType" + "|@Type" + "|@TypeBinderType" + 
    		"|@TypeRegistration" + "|@TypeRegistrations" + "|@UpdateTimestamp" + "|@UuidGenerator.Style" + "|@UuidGenerator" + 
    		"|@ValueGenerationType" + "|@View" + "|@Where" + "|@WhereJoinTable";

    public static final String JPA_ANNOTATIONS = "@Access" + "|@AssociationOverride" + "|@AssociationOverrides"
            + "|@AttributeOverride" + "|@AttributeOverrides" + "|@Basic" + "|@Cacheable" + "|@CollectionTable"
            + "|@Column" + "|@ColumnResult" + "|@ConstructorResult" + "|@Convert" + "|@Converter" + "|@Converts"
            + "|@DiscriminatorColumn" + "|@DiscriminatorValue" + "|@ElementCollection" + "|@Embeddable" + "|@Embedded"
            + "|@EmbeddedId" + "|@Entity" + "|@EntityListeners" + "|@EntityResult" + "|@Enumerated"
            + "|@ExcludeDefaultListeners" + "|@ExcludeSuperclassListeners" + "|@FieldResult" + "|@ForeignKey"
            + "|@GeneratedValue" + "|@Id" + "|@IdClass" + "|@Index" + "|@Inheritance" + "|@JoinColumn" + "|@JoinColumns"
            + "|@JoinTable" + "|@Lob" + "|@ManyToMany" + "|@ManyToOne" + "|@MapKey" + "|@MapKeyClass" + "|@MapKeyColumn"
            + "|@MapKeyEnumerated" + "|@MapKeyJoinColumn" + "|@MapKeyJoinColumns" + "|@MapKeyTemporal"
            + "|@MappedSuperclass" + "|@MapsId" + "|@NamedAttributeNode" + "|@NamedEntityGraph" + "|@NamedEntityGraphs"
            + "|@NamedNativeQueries" + "|@NamedNativeQuery" + "|@NamedQueries" + "|@NamedQuery"
            + "|@NamedStoredProcedureQueries" + "|@NamedStoredProcedureQuery" + "|@NamedSubgraph" + "|@OneToMany"
            + "|@OneToOne" + "|@OrderBy" + "|@OrderColumn" + "|@PersistenceContext" + "|@PersistenceContexts"
            + "|@PersistenceProperty" + "|@PersistenceUnit" + "|@PersistenceUnits" + "|@PostLoad" + "|@PostPersist"
            + "|@PostRemove" + "|@PostUpdate" + "|@PrePersist" + "|@PreRemove" + "|@PreUpdate"
            + "|@PrimaryKeyJoinColumn" + "|@PrimaryKeyJoinColumns" + "|@QueryHint" + "|@SecondaryTable"
            + "|@SecondaryTables" + "|@SequenceGenerator" + "|@SequenceGenerators" + "|@SqlResultSetMapping"
            + "|@SqlResultSetMappings" + "|@StoredProcedureParameter" + "|@Table" + "|@TableGenerator"
            + "|@TableGenerators" + "|@Temporal" + "|@Transient" + "|@UniqueConstraint" + "|@Version";

    private Annotations() {
    }
}
